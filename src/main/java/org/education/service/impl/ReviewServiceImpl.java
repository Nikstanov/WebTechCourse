package org.education.service.impl;

import org.education.DAO.MovieDAO;
import org.education.DAO.ReviewDAO;
import org.education.DAO.UserDAO;
import org.education.DAO.exception.DatabaseQueryException;
import org.education.beans.Movie;
import org.education.beans.Review;
import org.education.beans.User;
import org.education.service.ReviewService;
import org.education.service.exception.ServiceException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ReviewServiceImpl implements ReviewService {

    private final ReviewDAO reviewDAO;
    private final MovieDAO movieDAO;

    public ReviewServiceImpl(ReviewDAO reviewDAO, MovieDAO movieDAO) {
        this.reviewDAO = reviewDAO;
        this.movieDAO = movieDAO;
    }

    @Override
    public void addOrChangeReview(Movie movie, User user, int mark, String review) throws ServiceException {
        try {

            Optional<Review> userReview = reviewDAO.getReviewByUserAndMovie(movie, user);
            if(userReview.isPresent()){
                Review obj = userReview.get();
                obj.setReview(review);
                obj.setMark(mark);
                reviewDAO.saveReview(obj, false);
            }
            else{
                reviewDAO.saveReview(
                        Review.builder()
                                .movie(movie)
                                .user(user)
                                .mark(mark)
                                .review(review)
                                .build()
                , true);
            }
            movie.setAverageMark(reviewDAO.getAverageMark(movie));
            movieDAO.updateMovieMark(movie);


        } catch (DatabaseQueryException e) {
            throw new ServiceException(e.getMessage());
        }
    }
}
