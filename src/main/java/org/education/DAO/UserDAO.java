package org.education.DAO;

import org.education.DAO.exception.DatabaseQueryException;
import org.education.beans.User;

import java.util.List;
import java.util.Optional;

public interface UserDAO {

    /**
     *
     * @return список всех пользователей
     * @throws DatabaseQueryException ошибка базы данных
     */
    List<User> getUsers() throws DatabaseQueryException;

    /**
     * @param name
     * @return
     * @throws DatabaseQueryException
     */
    Optional<User> getUserByUsername(String name) throws DatabaseQueryException;

    /**
     *
     * @param name
     * @return
     * @throws DatabaseQueryException
     */
    Optional<User> getUserByEmail(String name) throws DatabaseQueryException;

    /**
     *
     * @param id
     * @return
     * @throws DatabaseQueryException
     */
    Optional<User> getUserById(int id) throws DatabaseQueryException;

    /**
     *
     * @param user
     * @throws DatabaseQueryException
     */
    void saveUser(User user) throws DatabaseQueryException;


    /**
     *
     * @param user
     * @throws DatabaseQueryException
     */
    void deleteUser(int user) throws DatabaseQueryException;
}
