package org.education.DAO;

import org.education.DAO.exception.DatabaseQueryException;
import org.education.beans.Movie;

import java.util.List;
import java.util.Optional;

public interface MovieDAO {

    /**
     *
     * @return список всех фильмов
     * @throws DatabaseQueryException ошибка базы данных
     */
    List<Movie> getMovies() throws DatabaseQueryException;

    /**
     *
     * @param name имя фильма
     * @return найденный фильм
     * @throws DatabaseQueryException ошибка базы данных
     */
    Optional<Movie> getMovieByName(String name) throws DatabaseQueryException;

    /**
     * @param id айди фильма
     * @return найденный фильм
     * @throws DatabaseQueryException ошибка базы данных
     */
    Optional<Movie> getMovieById(int id) throws DatabaseQueryException;

    /**
     * @param movie обновляемый фильм
     * @throws DatabaseQueryException ошибка базы данных
     */
    void updateMovieMark(Movie movie) throws DatabaseQueryException;
}
