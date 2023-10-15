package org.education.DAO;

import org.education.DAO.exception.DBException;
import org.education.DAO.impl.ConnectionPool;
import org.education.DAO.impl.UserDAOImpl;
import org.education.beans.Role;
import org.education.beans.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class UserDAO implements UserDAOImpl {

    ConnectionPool connectionPool = ConnectionPoolFabric.getConnectionPool();

    @Override
    public Optional<User> getUserByName(String name) throws DBException {
        Optional<User> res = Optional.empty();
        Connection connection = connectionPool.getConnection();
        String sql = "SELECT * FROM users where login = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1, name);
            try(ResultSet rs = preparedStatement.executeQuery()){
                if(rs.next()){
                    res = Optional.of(User.builder()
                            .login(rs.getString("login"))
                            .username(rs.getString("username"))
                            .role(Role.valueOf(rs.getString("role")))
                            .build());
                }
            }
        } catch (SQLException e) {
            throw new DBException("Some problems with database");
        }
        finally {
            connectionPool.releaseConnection(connection);
        }
        return res;
    }
}
