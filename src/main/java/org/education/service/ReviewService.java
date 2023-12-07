package org.education.service;

import org.education.beans.Movie;
import org.education.beans.User;
import org.education.service.exception.ServiceException;

public interface ReviewService {

    /**
     *
     * @param movie
     * @param user
     * @param mark
     * @param review
     * @throws ServiceException
     */
    void addOrChangeReview(Movie movie, User user, int mark, String review) throws ServiceException;

}
