package org.education.DAO.impl;

import org.education.DAO.MovieDAO;
import org.education.DAO.connection.ConnectionPool;
import org.education.DAO.exception.DatabaseQueryException;
import org.education.beans.Movie;
import org.education.utills.mapper.MovieMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MovieDAOImpl implements MovieDAO {

    ConnectionPool connectionPool = ConnectionPool.getInstance();

    @Override
    public List<Movie> getMovies() throws DatabaseQueryException{
        List<Movie> res = new ArrayList<>();
        try(Connection connection = connectionPool.getConnection()){
            String sql = "SELECT * FROM movies";
            try(Statement statement = connection.createStatement()) {
                try(ResultSet rs = statement.executeQuery(sql)){
                    while(rs.next()){
                        res.add(MovieMapper.mapperMovie(rs));
                    }
                }
            } catch (SQLException e) {
                throw new DatabaseQueryException("Some problems with database");
            }
        }
        catch (SQLException e) {
            throw new DatabaseQueryException("Some problems with database");
        }
        return res;
    }

    @Override
    public Optional<Movie> getMovieByName(String name) throws DatabaseQueryException {
        Optional<Movie> res = Optional.empty();
        try(Connection connection = connectionPool.getConnection()){
            String sql = "SELECT * FROM Movies WHERE name = ?";
            try(PreparedStatement statement = connection.prepareStatement(sql)){
                statement.setString(1, name);
                try(ResultSet rs = statement.executeQuery()){
                    if(rs.next()){
                        res = Optional.of(MovieMapper.mapperMovie(rs));
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
    public Optional<Movie> getMovieById(int id) throws DatabaseQueryException {
        Optional<Movie> res = Optional.empty();
        try(Connection connection = connectionPool.getConnection()){
            String sql = "SELECT * FROM Movies WHERE movie_id = ?";
            try(PreparedStatement statement = connection.prepareStatement(sql)){
                statement.setInt(1, id);
                try(ResultSet rs = statement.executeQuery()){
                    if(rs.next()){
                        res = Optional.of(MovieMapper.mapperMovie(rs));
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
    public void updateMovieMark(Movie movie) throws DatabaseQueryException {
        try(Connection connection = connectionPool.getConnection()){
            String sql = "UPDATE movies SET averagemark = ? WHERE movie_id = ?";
            try(PreparedStatement statement = connection.prepareStatement(sql)){
                statement.setDouble(1, movie.getAverageMark());
                statement.setInt(2, movie.getId());
                statement.executeUpdate();
            } catch (SQLException e) {
                throw new DatabaseQueryException(e.getMessage());
            }
        } catch (SQLException e) {
            throw new DatabaseQueryException(e.getMessage());
        }
    }
}
