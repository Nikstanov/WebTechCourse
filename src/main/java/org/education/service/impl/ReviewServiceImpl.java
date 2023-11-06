package org.education.service.impl;

import org.education.DAO.DAOFactory;
import org.education.DAO.MovieDAO;
import org.education.DAO.ReviewDAO;
import org.education.DAO.UserDAO;
import org.education.DAO.exception.DatabaseQueryException;
import org.education.beans.Movie;
import org.education.beans.Review;
import org.education.beans.User;
import org.education.service.ReviewService;
import org.education.service.ServiceFactory;
import org.education.service.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public class ReviewServiceImpl implements ReviewService {

    ReviewDAO reviewDAO = DAOFactory.getInstance().getReviewDAO();
    UserDAO userDAO = DAOFactory.getInstance().getUserDAO();
    MovieDAO movieDAO = DAOFactory.getInstance().getMovieDAO();

    @Override
    public void addOrChangeReview(Movie movie, User user, int mark, String review) throws ServiceException {
        try {

            Optional<Review> userReview = reviewDAO.getReviewByUserAndMovie(movie, user);
            if(userReview.isPresent()){
                Review obj = userReview.get();
                obj.setReview(review);
                obj.setMark(mark);
                reviewDAO.updateReview(obj);
            }
            else{
                reviewDAO.saveReview(
                        Review.builder()
                                .movie_id(movie.getId())
                                .user_id(user.getId())
                                .mark(mark)
                                .review(review)
                                .build()
                );
            }
            movie.setAverageMark(reviewDAO.getAverageMark(movie));
            movieDAO.updateMovieMark(movie);

            ServiceFactory.getInstance().getUserService().updateRate();

        } catch (DatabaseQueryException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public List<Review> getMoviesReviews(Movie movie) throws ServiceException {
        try {
            return reviewDAO.getReviewsByMovie(movie);
        } catch (DatabaseQueryException e) {
            throw new ServiceException(e.getMessage());
        }
    }
}
