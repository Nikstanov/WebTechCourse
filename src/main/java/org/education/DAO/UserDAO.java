package org.education.DAO;

import org.education.DAO.exception.DatabaseQueryException;
import org.education.beans.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserDAO extends JpaRepository<User, Integer> {

    User getUserByUsername(String name);
    User getUserByLogin(String email);
    User getUserById(int id);
    void deleteAllById(int id);
}
