package org.education.utills.mapper;

import org.education.beans.Review;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReviewMapper {

    /**
     *
     * @param rs
     * @return
     * @throws SQLException
     */
    public static Review reviewMapper(ResultSet rs) throws SQLException {
        return Review.builder()
                .user_id(rs.getInt("user_id"))
                .movie_id(rs.getInt("movie_id"))
                .mark(rs.getInt("mark"))
                .review(rs.getString("review"))
                .build();
    }
}
