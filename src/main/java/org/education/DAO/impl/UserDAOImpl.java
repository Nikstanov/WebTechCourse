package org.education.DAO.impl;

import org.education.DAO.UserDAO;
import org.education.DAO.exception.DatabaseQueryException;
import org.education.beans.Movie;
import org.education.beans.Role;
import org.education.beans.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UserDAOImpl implements UserDAO {

//    ConnectionPool connectionPool = ConnectionPool.getInstance();
    private final SessionFactory sessionFactory;

    @Autowired
    public UserDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<User> getUsers() throws DatabaseQueryException {
        return sessionFactory.fromTransaction(session -> session.createSelectionQuery("from User", User.class).getResultList());
//        List<User> res = new ArrayList<>();
//        try(Connection connection = connectionPool.getConnection()){
//            String sql = "SELECT * FROM users";
//            try(Statement statement = connection.createStatement()) {
//                try(ResultSet rs = statement.executeQuery(sql)){
//                    while(rs.next()){
//                        res.add(UserMapper.mapperUser(rs));
//                    }
//                }
//            } catch (SQLException e) {
//                throw new DatabaseQueryException("Some problems with database");
//            }
//        }
//        catch (SQLException e) {
//            throw new DatabaseQueryException("Some problems with database");
//        }
//        return res;
    }

    @Override
    public Optional<User> getUserByUsername(String name) throws DatabaseQueryException {
        return sessionFactory.fromTransaction(session -> {
            var query = session.createSelectionQuery("from User where username = :name", User.class);
            query.setParameter("name",name);
            User user = query.getSingleResultOrNull();
            if (user == null) return Optional.empty();
            else return Optional.of(user);
        });
//        Optional<User> res = Optional.empty();
//        try(Connection connection = connectionPool.getConnection()){
//            String sql = "SELECT * FROM users WHERE username = ?";
//            try(PreparedStatement statement = connection.prepareStatement(sql)){
//                statement.setString(1, name);
//                try(ResultSet rs = statement.executeQuery()){
//                    if(rs.next()){
//                        res = Optional.of(UserMapper.mapperUser(rs));
//                    }
//                }
//            } catch (SQLException e) {
//                throw new DatabaseQueryException(e.getMessage());
//            }
//        } catch (SQLException e) {
//            throw new DatabaseQueryException(e.getMessage());
//        }
//        return res;
    }

    @Override
    public Optional<User> getUserByEmail(String name) throws DatabaseQueryException {
        return sessionFactory.fromTransaction(session -> {
            var query = session.createSelectionQuery("from User where login = :name", User.class);
            query.setParameter("name",name);
            User user = query.getSingleResultOrNull();
            if (user == null) return Optional.empty();
            else return Optional.of(user);
        });
//        Optional<User> res = Optional.empty();
//        try(Connection connection = connectionPool.getConnection()){
//            String sql = "SELECT * FROM users WHERE login = ?";
//            try(PreparedStatement statement = connection.prepareStatement(sql)){
//                statement.setString(1, name);
//                try(ResultSet rs = statement.executeQuery()){
//                    if(rs.next()){
//                        res = Optional.of(UserMapper.mapperUser(rs));
//                    }
//                }
//            } catch (SQLException e) {
//                throw new DatabaseQueryException(e.getMessage());
//            }
//        } catch (SQLException e) {
//            throw new DatabaseQueryException(e.getMessage());
//        }
//        return res;
    }

    @Override
    public Optional<User> getUserById(int id) throws DatabaseQueryException {
        return sessionFactory.fromTransaction(session -> {
            var query = session.createSelectionQuery("from User where id = :id", User.class);
            query.setParameter("id",id);
            User user = query.getSingleResultOrNull();
            if (user == null) return Optional.empty();
            else return Optional.of(user);
        });
//        Optional<User> res = Optional.empty();
//        try(Connection connection = connectionPool.getConnection()){
//            String sql = "SELECT * FROM users WHERE user_id = ?";
//            try(PreparedStatement statement = connection.prepareStatement(sql)){
//                statement.setInt(1, id);
//                try(ResultSet rs = statement.executeQuery()){
//                    if(rs.next()){
//                        res = Optional.of(UserMapper.mapperUser(rs));
//                    }
//                }
//            } catch (SQLException e) {
//                throw new DatabaseQueryException(e.getMessage());
//            }
//        } catch (SQLException e) {
//            throw new DatabaseQueryException(e.getMessage());
//        }
//        return res;
    }

    @Override
    public void saveUser(User user) throws DatabaseQueryException {
        sessionFactory.inTransaction(session -> session.merge(user));
//        try(Connection connection = connectionPool.getConnection()){
//            String sql = "INSERT INTO users (login, password, username, token, role) VALUES (?, ?, ?, ?, ?)";
//            try(PreparedStatement statement = connection.prepareStatement(sql)){
//                statement.setString(1, user.getLogin());
//                statement.setString(2, user.getPassword());
//                statement.setString(3, user.getUsername());
//                statement.setString(4, user.getToken());
//                statement.setString(5, Role.USER.toString());
//                statement.execute();
//            } catch (SQLException e) {
//                throw new DatabaseQueryException(e.getMessage());
//            }
//        } catch (SQLException e) {
//            throw new DatabaseQueryException(e.getMessage());
//        }
    }

//    @Override
//    public void updateUser(User user) throws DatabaseQueryException {
//        try(Connection connection = connectionPool.getConnection()){
//            String sql = "UPDATE users SET login = ?, password = ?, username = ?, socialcredit = ?, role = ?, token = ? WHERE user_id = ?";
//            try(PreparedStatement statement = connection.prepareStatement(sql)){
//                statement.setString(1, user.getLogin());
//                statement.setString(2, user.getPassword());
//                statement.setString(3, user.getUsername());
//                statement.setInt(4, user.getSocialCredit());
//                statement.setString(5, user.getRole().toString());
//                statement.setString(6, user.getToken());
//                statement.setInt(7, user.getId());
//                statement.executeUpdate();
//            } catch (SQLException e) {
//                throw new DatabaseQueryException(e.getMessage());
//            }
//        } catch (SQLException e) {
//            throw new DatabaseQueryException(e.getMessage());
//        }
//    }

    @Override
    public void deleteUser(int user_id) throws DatabaseQueryException {
        sessionFactory.inTransaction(session -> {
            User user = session.createSelectionQuery("from User where id = :id", User.class).getSingleResultOrNull();
            if (user != null) session.remove(user);
        });
//        try(Connection connection = connectionPool.getConnection()){
//            String sql = "DELETE FROM users WHERE user_id = ?";
//            try(PreparedStatement statement = connection.prepareStatement(sql)){
//                statement.setInt(1, user);
//                statement.executeUpdate();
//            } catch (SQLException e) {
//                throw new DatabaseQueryException(e.getMessage());
//            }
//        } catch (SQLException e) {
//            throw new DatabaseQueryException(e.getMessage());
//        }
    }
}
