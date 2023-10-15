package org.education.DAO;

import org.education.DAO.impl.ConnectionPool;

import java.sql.SQLException;

public class ConnectionPoolFabric {
    private static final String USERNAME = "Nikstanov";
    private static final String PASSWORD = "15100103";
    private static final String URL = "jdbc:postgresql://localhost:5051/moviedb";

    private static ConnectionPool connectionPool;

    static {
        try {
            connectionPool = BasicConnectionPool.create(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static ConnectionPool getConnectionPool(){
        return connectionPool;
    }
}
