package org.education.DAO;

import org.education.DAO.exception.DatabaseQueryException;
import org.education.beans.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MovieDAO extends JpaRepository<Movie, Integer> {

    Movie getMovieByName(String name);
    Movie getMovieById(int id);

}
