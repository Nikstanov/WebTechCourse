package org.education.service;

import org.education.beans.Movie;
import org.education.service.exception.ServiceException;

import java.util.List;
import java.util.Optional;


public interface MovieService {

    List<Movie> getMovies(int pageInd) throws ServiceException;
    Optional<Movie> getMovieByName(String name);
    Optional<Movie> getMovieById(int id) throws ServiceException;
    int getPageCount() throws ServiceException;
}
