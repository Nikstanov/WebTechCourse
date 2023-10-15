package org.education.DAO.impl;

import org.education.DAO.exception.DBException;
import org.education.beans.User;

import java.util.Optional;

public interface UserDAOImpl {
    public Optional<User> getUserByName(String name) throws DBException;
}
