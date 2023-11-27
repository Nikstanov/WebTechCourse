package org.education.service.impl;

import org.education.DAO.MovieDAO;
import org.education.DAO.exception.DatabaseQueryException;
import org.education.beans.Movie;
import org.education.service.MovieService;
import org.education.service.exception.ServiceException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class MovieServiceImpl implements MovieService {

    private final MovieDAO movieDAO;

    private final int MOVIES_FOR_PAGE = 3;

    public MovieServiceImpl(MovieDAO movieDAO) {
        this.movieDAO = movieDAO;
    }

    @Override
    public List<Movie> getMovies(int pageInd) throws ServiceException {
        List<Movie> res;
        try{
            pageInd *= MOVIES_FOR_PAGE;
            res = movieDAO.getMovies();
            int lastMovie = pageInd + MOVIES_FOR_PAGE;
            if( lastMovie >= res.size()){
                lastMovie = res.size();
            }
            res = res.subList(pageInd,lastMovie);

        }
        catch (DatabaseQueryException e) {
            throw new ServiceException(e.getMessage());
        }
        return res;
    }

    @Override
    public Optional<Movie> getMovieByName(String name) {
        return Optional.empty();
    }

    @Override
    public Optional<Movie> getMovieById(int id) throws ServiceException {
        try{
            return movieDAO.getMovieById(id);
        } catch (DatabaseQueryException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public int getPageCount() throws ServiceException {
        try{
            int size = movieDAO.getMovies().size();
            if(size % MOVIES_FOR_PAGE == 0){
                return size / MOVIES_FOR_PAGE - 1;
            }
            return size / MOVIES_FOR_PAGE;
        }
        catch (DatabaseQueryException e) {
            throw new ServiceException(e.getMessage());
        }
    }
}
