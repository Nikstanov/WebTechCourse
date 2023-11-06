package org.education.DAO;

import lombok.Getter;
import org.education.DAO.impl.MovieDAOImpl;
import org.education.DAO.impl.ReviewDAOImpl;
import org.education.DAO.impl.UserDAOImpl;

public class DAOFactory {
    private final static DAOFactory Instance;

    static{
        Instance = new DAOFactory();
    }

    private DAOFactory(){
        movieDAO = new MovieDAOImpl();
        userDAO = new UserDAOImpl();
        reviewDAO = new ReviewDAOImpl();
    }

    public static DAOFactory getInstance(){
        return Instance;
    }

    @Getter
    private final MovieDAO movieDAO;
    @Getter
    private final UserDAO userDAO;
    @Getter
    private final ReviewDAO reviewDAO;

}
