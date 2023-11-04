package org.education.DAO;

import org.education.DAO.exception.DatabaseQueryException;
import org.education.beans.Movie;

import java.util.List;
import java.util.Optional;

public interface MovieDAO {
    List<Movie> getMovies() throws DatabaseQueryException;
    Optional<Movie> getMovieByName(String name) throws DatabaseQueryException;
    Optional<Movie> getMovieById(int id) throws DatabaseQueryException;
}
