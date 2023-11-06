package org.education.DAO;

import org.education.DAO.exception.DatabaseQueryException;
import org.education.beans.User;

import java.util.List;
import java.util.Optional;

public interface UserDAO {

    List<User> getUsers() throws DatabaseQueryException;
    Optional<User> getUserByUsername(String name) throws DatabaseQueryException;
    Optional<User> getUserByEmail(String name) throws DatabaseQueryException;
    Optional<User> getUserById(int id) throws DatabaseQueryException;
    void saveUser(User user) throws DatabaseQueryException;
    void updateUser(User user) throws DatabaseQueryException;
}
