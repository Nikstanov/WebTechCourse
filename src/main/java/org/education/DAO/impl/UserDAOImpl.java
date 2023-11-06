package org.education.DAO.impl;

import com.google.common.hash.Hashing;
import org.education.DAO.UserDAO;
import org.education.DAO.connection.ConnectionPool;
import org.education.DAO.exception.DatabaseQueryException;
import org.education.beans.Role;
import org.education.beans.User;
import org.education.utills.mapper.UserMapper;

import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDAOImpl implements UserDAO {

    ConnectionPool connectionPool = ConnectionPool.getInstance();

    @Override
    public List<User> getUsers() throws DatabaseQueryException {
        List<User> res = new ArrayList<>();
        try(Connection connection = connectionPool.getConnection()){
            String sql = "SELECT * FROM movies";
            try(Statement statement = connection.createStatement()) {
                try(ResultSet rs = statement.executeQuery(sql)){
                    while(rs.next()){
                        res.add(UserMapper.mapperMovie(rs));
                    }
                }
            } catch (SQLException e) {
                throw new DatabaseQueryException("Some problems with database");
            }
        }
        catch (SQLException e) {
            throw new DatabaseQueryException("Some problems with database");
        }
        return res;
    }

    @Override
    public Optional<User> getUserByUsername(String name) throws DatabaseQueryException {
        Optional<User> res = Optional.empty();
        try(Connection connection = connectionPool.getConnection()){
            String sql = "SELECT * FROM users WHERE username = ?";
            try(PreparedStatement statement = connection.prepareStatement(sql)){
                statement.setString(1, name);
                try(ResultSet rs = statement.executeQuery()){
                    if(rs.next()){
                        res = Optional.of(UserMapper.mapperMovie(rs));
                    }
                }
            } catch (SQLException e) {
                throw new DatabaseQueryException(e.getMessage());
            }
        } catch (SQLException e) {
            throw new DatabaseQueryException(e.getMessage());
        }
        return res;
    }

    @Override
    public Optional<User> getUserByEmail(String name) throws DatabaseQueryException {
        Optional<User> res = Optional.empty();
        try(Connection connection = connectionPool.getConnection()){
            String sql = "SELECT * FROM users WHERE login = ?";
            try(PreparedStatement statement = connection.prepareStatement(sql)){
                statement.setString(1, name);
                try(ResultSet rs = statement.executeQuery()){
                    if(rs.next()){
                        res = Optional.of(UserMapper.mapperMovie(rs));
                    }
                }
            } catch (SQLException e) {
                throw new DatabaseQueryException(e.getMessage());
            }
        } catch (SQLException e) {
            throw new DatabaseQueryException(e.getMessage());
        }
        return res;
    }

    @Override
    public Optional<User> getUserById(int id) throws DatabaseQueryException {
        Optional<User> res = Optional.empty();
        try(Connection connection = connectionPool.getConnection()){
            String sql = "SELECT * FROM users WHERE user_id = ?";
            try(PreparedStatement statement = connection.prepareStatement(sql)){
                statement.setInt(1, id);
                try(ResultSet rs = statement.executeQuery()){
                    if(rs.next()){
                        res = Optional.of(UserMapper.mapperMovie(rs));
                    }
                }
            } catch (SQLException e) {
                throw new DatabaseQueryException(e.getMessage());
            }
        } catch (SQLException e) {
            throw new DatabaseQueryException(e.getMessage());
        }
        return res;
    }

    @Override
    public void saveUser(User user) throws DatabaseQueryException {
        try(Connection connection = connectionPool.getConnection()){
            String sql = "INSERT INTO users (login, password, username, token, role) VALUES (?, ?, ?, ?, ?)";
            try(PreparedStatement statement = connection.prepareStatement(sql)){
                statement.setString(1, user.getLogin());
                statement.setString(2, user.getPassword());
                statement.setString(3, user.getUsername());
                statement.setString(4, user.getToken());
                statement.setString(5, Role.USER.toString());
                statement.execute();
            } catch (SQLException e) {
                throw new DatabaseQueryException(e.getMessage());
            }
        } catch (SQLException e) {
            throw new DatabaseQueryException(e.getMessage());
        }
    }

    @Override
    public void updateUser(User user) throws DatabaseQueryException {
        try(Connection connection = connectionPool.getConnection()){
            String sql = "UPDATE users SET login = ?, password = ?, username = ?, socialcredit = ?, role = ?, banned = ?, token = ? WHERE user_id = ?";
            try(PreparedStatement statement = connection.prepareStatement(sql)){
                statement.setString(1, user.getLogin());
                statement.setString(2, user.getPassword());
                statement.setString(3, user.getUsername());
                statement.setInt(4, user.getSocialCredit());
                statement.setString(5, user.getRole().toString());
                statement.setBoolean(6, user.isBanned());
                statement.setString(7, user.getToken());
                statement.setInt(8, user.getId());
                statement.executeUpdate();
            } catch (SQLException e) {
                throw new DatabaseQueryException(e.getMessage());
            }
        } catch (SQLException e) {
            throw new DatabaseQueryException(e.getMessage());
        }
    }
}
