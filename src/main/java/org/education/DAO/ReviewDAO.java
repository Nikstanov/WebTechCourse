package org.education.DAO;

import org.education.DAO.exception.DatabaseQueryException;
import org.education.beans.Movie;
import org.education.beans.Review;
import org.education.beans.ReviewID;
import org.education.beans.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ReviewDAO extends JpaRepository<Review, ReviewID> {

    @Query("SELECT avg (mark) from Review where movie.id = :id")
    double getAverageMark(@Param(value = "id")int id);
    Review getReviewByMovieAndUser(Movie movie, User user);

}
