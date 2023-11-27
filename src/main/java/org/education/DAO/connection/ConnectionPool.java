package org.education.DAO.connection;

import org.education.DAO.exception.ConnectionException;
import org.postgresql.core.ConnectionFactory;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ConnectionPool {

    static final ConnectionPool instance = new ConnectionPool();

    private static final String POOL_SIZE = "database.pool_size";
    private static final String DB_CONNECTION_PATH = "database.properties";
    private static final String DB_URL = "database.url";
    private static final String DB_USER = "database.username";
    private static final String DB_PASSWORD = "database.password";
    private static final String DB_DRIVER = "database.driver";

    private BlockingQueue<ProxyConnection> availableConnections;
    private BlockingQueue<ProxyConnection> usedConnections;

    public static ConnectionPool getInstance() {
        return instance;
    }

    private ConnectionPool() {}

    static ProxyConnection createConnection(Properties dbProperties) throws ConnectionException {
        ProxyConnection proxyConnection;
        try {
            String dbUrl = dbProperties.getProperty(DB_URL);
            String dbUser = dbProperties.getProperty(DB_USER);
            String dbPassword = dbProperties.getProperty(DB_PASSWORD);
            Class.forName(dbProperties.getProperty(DB_DRIVER));
            proxyConnection = new ProxyConnection(DriverManager.getConnection(dbUrl, dbUser, dbPassword));
        } catch (SQLException | ClassNotFoundException e) {
            throw new ConnectionException(e.getMessage(), e);
        }
        return proxyConnection;
    }

    public void initialize() throws ConnectionException {
        try {
            Properties dbProperties = new Properties();
            dbProperties.load(ConnectionFactory.class.getClassLoader().getResourceAsStream(DB_CONNECTION_PATH));
            int poolSize = Integer.parseInt(dbProperties.getProperty(POOL_SIZE));
            availableConnections = new ArrayBlockingQueue<>(poolSize);
            usedConnections = new ArrayBlockingQueue<>(poolSize);
            for (int i = 0; i < poolSize; i++) {
                ProxyConnection connection = createConnection(dbProperties);
                availableConnections.add(connection);
            }
        } catch (IOException e) {
            throw new ConnectionException(e.getMessage(), e);
        }

    }

    public void releaseConnection(ProxyConnection proxyConnection) throws ConnectionException {
        if (proxyConnection != null) {
            if(usedConnections.remove(proxyConnection)){
                try {
                    availableConnections.put(proxyConnection);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    throw new ConnectionException(e.getMessage(), e);
                }
            }
        }
    }

    public ProxyConnection getConnection() throws ConnectionException {
        ProxyConnection connection;
        try {
            connection = availableConnections.take();
            usedConnections.put(connection);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new ConnectionException(e.getMessage(), e);
        }
        return connection;
    }

    public void destroy() throws ConnectionException {
        try {
            for (ProxyConnection connection : availableConnections) {
                connection.closeConnection();
            }
            for (ProxyConnection connection : usedConnections) {
                connection.closeConnection();
            }
        } catch (SQLException e) {
            throw new ConnectionException(e.getMessage(), e);
        }

    }
}
