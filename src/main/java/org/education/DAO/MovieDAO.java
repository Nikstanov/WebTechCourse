package org.education.DAO;

import org.education.DAO.exception.DBException;
import org.education.DAO.impl.ConnectionPool;
import org.education.DAO.impl.MovieDAOImpl;
import org.education.beans.Movie;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MovieDAO implements MovieDAOImpl {

    ConnectionPool connectionPool = ConnectionPoolFabric.getConnectionPool();

    @Override
    public List<Movie> getMovies() throws DBException {
        List<Movie> res = new ArrayList<>();
        Connection connection = connectionPool.getConnection();
        String sql = "SELECT * FROM movies";
        try(Statement statement = connection.createStatement()) {
            try(ResultSet rs = statement.executeQuery(sql)){
                while(rs.next()){
                    res.add(Movie.builder()
                            .name(rs.getString("name"))
                            .author(rs.getString("author"))
                            .averageMark(rs.getDouble("averageMark"))
                            .description(rs.getString("description"))
                            .shortDescription(rs.getString("short_description"))
                            .build());
                }
            }
        } catch (SQLException e) {
            throw new DBException("Some problems with database");
        }
        finally {
            connectionPool.releaseConnection(connection);
        }
        return res;
    }
}
