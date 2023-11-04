package org.education.DAO;

import org.education.DAO.impl.MovieDAOImpl;

public class DAOFactory {
    private final static DAOFactory Instance;

    static{
        Instance = new DAOFactory();
    }

    private DAOFactory(){
        movieDAO = new MovieDAOImpl();
    }

    public static DAOFactory getInstance(){
        return Instance;
    }

    private final MovieDAO movieDAO;

    public MovieDAO getMovieDAO(){return movieDAO;}
}
