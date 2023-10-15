package org.education.DAO.impl;

import org.education.DAO.exception.DBException;
import org.education.beans.Movie;
import org.education.beans.User;

import java.util.List;

public interface MovieDAOImpl {
    public List<Movie> getMovies() throws DBException;
}
