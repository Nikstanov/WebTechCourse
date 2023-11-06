package org.education.DAO.impl;

import org.education.DAO.ReviewDAO;
import org.education.DAO.connection.ConnectionPool;
import org.education.DAO.exception.DatabaseQueryException;
import org.education.beans.Movie;
import org.education.beans.Review;
import org.education.beans.User;
import org.education.utills.mapper.ReviewMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ReviewDAOImpl implements ReviewDAO {

    ConnectionPool connectionPool = ConnectionPool.getInstance();

    @Override
    public List<Review> getReviewsByMovie(Movie movie) throws DatabaseQueryException {
        List<Review> res = new ArrayList<>();
        try(Connection connection = connectionPool.getConnection()){
            String sql = "SELECT reviews.user_id, movie_id, review, mark, username, socialCredit FROM reviews LEFT JOIN users u on u.user_id = reviews.user_id WHERE movie_id = ?";
            try(PreparedStatement statement = connection.prepareStatement(sql)){
                statement.setInt(1, movie.getId());
                try(ResultSet rs = statement.executeQuery()){
                    while(rs.next()){
                        Review review = ReviewMapper.reviewMapper(rs);
                        review.setOwnerName(rs.getString("username"));
                        review.setSocialCredit(rs.getInt("socialCredit"));
                        res.add(review);
                    }
                }
            } catch (SQLException e) {
                throw new DatabaseQueryException(e.getMessage());
            }
        } catch (SQLException e) {
            throw new DatabaseQueryException(e.getMessage());
        }
        return res;
    }

    @Override
    public List<Review> getReviewsByUser(User user) throws DatabaseQueryException {
        List<Review> res = new ArrayList<>();
        try(Connection connection = connectionPool.getConnection()){
            String sql = "SELECT * FROM reviews WHERE user_id = ?";
            try(PreparedStatement statement = connection.prepareStatement(sql)){
                statement.setInt(1, user.getId());
                try(ResultSet rs = statement.executeQuery()){
                    while(rs.next()){
                        res.add(ReviewMapper.reviewMapper(rs));
                    }
                }
            } catch (SQLException e) {
                throw new DatabaseQueryException(e.getMessage());
            }
        } catch (SQLException e) {
            throw new DatabaseQueryException(e.getMessage());
        }
        return res;
    }

    @Override
    public double getAverageMark(Movie movie) throws DatabaseQueryException {
        try(Connection connection = connectionPool.getConnection()){
            String sql = "SELECT AVG(mark) FROM reviews WHERE movie_id = ?";
            try(PreparedStatement statement = connection.prepareStatement(sql)){
                statement.setInt(1, movie.getId());
                try(ResultSet rs = statement.executeQuery()){
                    if(rs.next()){
                        return rs.getDouble("avg");
                    }
                    else{
                        throw new DatabaseQueryException("Unknown Error");
                    }
                }
            } catch (SQLException e) {
                throw new DatabaseQueryException(e.getMessage());
            }
        } catch (SQLException e) {
            throw new DatabaseQueryException(e.getMessage());
        }
    }

    @Override
    public Optional<Review> getReviewByUserAndMovie(Movie movie, User user) throws DatabaseQueryException {
        Optional<Review> res = Optional.empty();
        try(Connection connection = connectionPool.getConnection()){
            String sql = "SELECT * FROM reviews WHERE movie_id = ? AND user_id = ?";
            try(PreparedStatement statement = connection.prepareStatement(sql)){
                statement.setInt(1, movie.getId());
                statement.setInt(2, user.getId());
                try(ResultSet rs = statement.executeQuery()){
                    if(rs.next()){
                        res = Optional.of(ReviewMapper.reviewMapper(rs));
                    }
                }
            } catch (SQLException e) {
                throw new DatabaseQueryException(e.getMessage());
            }
        } catch (SQLException e) {
            throw new DatabaseQueryException(e.getMessage());
        }
        return res;
    }

    @Override
    public void saveReview(Review review) throws DatabaseQueryException {
        try(Connection connection = connectionPool.getConnection()){
            String sql = "INSERT INTO reviews (user_id, movie_id, review, mark) VALUES (?,?,?,?)";
            try(PreparedStatement statement = connection.prepareStatement(sql)){
                statement.setInt(1, review.getUser_id());
                statement.setInt(2, review.getMovie_id());
                statement.setString(3, review.getReview());
                statement.setInt(4, review.getMark());
                statement.execute();
            } catch (SQLException e) {
                throw new DatabaseQueryException(e.getMessage());
            }
        } catch (SQLException e) {
            throw new DatabaseQueryException(e.getMessage());
        }
    }

    @Override
    public void updateReview(Review review) throws DatabaseQueryException {
        try(Connection connection = connectionPool.getConnection()){
            String sql = "UPDATE reviews SET review = ?, mark = ? WHERE user_id = ? AND movie_id = ?";
            try(PreparedStatement statement = connection.prepareStatement(sql)){
                statement.setString(1, review.getReview());
                statement.setInt(2, review.getMark());
                statement.setInt(3, review.getUser_id());
                statement.setInt(4, review.getMovie_id());
                statement.executeUpdate();
            } catch (SQLException e) {
                throw new DatabaseQueryException(e.getMessage());
            }
        } catch (SQLException e) {
            throw new DatabaseQueryException(e.getMessage());
        }
    }
}
