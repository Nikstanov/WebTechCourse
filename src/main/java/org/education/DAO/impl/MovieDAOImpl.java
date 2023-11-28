package org.education.DAO.impl;

import org.education.DAO.MovieDAO;
import org.education.DAO.exception.DatabaseQueryException;
import org.education.beans.Movie;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class MovieDAOImpl implements MovieDAO {

    private  final SessionFactory sessionFactory;

    @Autowired
    public MovieDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Movie> getMovies() throws DatabaseQueryException{
        return sessionFactory.fromTransaction(session -> session.createSelectionQuery("from Movie", Movie.class).getResultList());
//        List<Movie> res = new ArrayList<>();
//        try(Connection connection = connectionPool.getConnection()){
//            String sql = "SELECT * FROM movies";
//            try(Statement statement = connection.createStatement()) {
//                try(ResultSet rs = statement.executeQuery(sql)){
//                    while(rs.next()){
//                        res.add(MovieMapper.mapperMovie(rs));
//                    }
//                }
//            } catch (SQLException e) {
//                throw new DatabaseQueryException("Some problems with database");
//            }
//        }
//        catch (SQLException e) {
//            throw new DatabaseQueryException("Some problems with database");
//        }
//        return res;
    }

    @Override
    public Optional<Movie> getMovieByName(String name) throws DatabaseQueryException {
        return sessionFactory.fromTransaction(session -> {
            var query = session.createSelectionQuery("from Movie where name = :name", Movie.class);
            query.setParameter("name",name);
            Movie movie = query.getSingleResultOrNull();
            if (movie == null) return Optional.empty();
            else return Optional.of(movie);
        });
//        Optional<Movie> res = Optional.empty();
//        try(Connection connection = connectionPool.getConnection()){
//            String sql = "SELECT * FROM Movies WHERE name = ?";
//            try(PreparedStatement statement = connection.prepareStatement(sql)){
//                statement.setString(1, name);
//                try(ResultSet rs = statement.executeQuery()){
//                    if(rs.next()){
//                        res = Optional.of(MovieMapper.mapperMovie(rs));
//                    }
//                }
//            } catch (SQLException e) {
//                throw new DatabaseQueryException(e.getMessage());
//            }
//        } catch (SQLException e) {
//            throw new DatabaseQueryException(e.getMessage());
//        }
//        return res;
    }

    @Override
    public Optional<Movie> getMovieById(int id) throws DatabaseQueryException {
        return sessionFactory.fromTransaction(session -> {
            var query = session.createSelectionQuery("from Movie where id = :id", Movie.class);
            query.setParameter("id",id);
            Movie movie = query.getSingleResultOrNull();
            if (movie == null) return Optional.empty();
            else return Optional.of(movie);
        });
//        Optional<Movie> res = Optional.empty();
//        try(Connection connection = connectionPool.getConnection()){
//            String sql = "SELECT * FROM Movies WHERE movie_id = ?";
//            try(PreparedStatement statement = connection.prepareStatement(sql)){
//                statement.setInt(1, id);
//                try(ResultSet rs = statement.executeQuery()){
//                    if(rs.next()){
//                        res = Optional.of(MovieMapper.mapperMovie(rs));
//                    }
//                }
//            } catch (SQLException e) {
//                throw new DatabaseQueryException(e.getMessage());
//            }
//        } catch (SQLException e) {
//            throw new DatabaseQueryException(e.getMessage());
//        }
//        return res;
    }

    @Override
    public void updateMovieMark(Movie movie) throws DatabaseQueryException {
        sessionFactory.inTransaction(session -> session.merge(movie));
//        try(Connection connection = connectionPool.getConnection()){
//            String sql = "UPDATE movies SET averagemark = ? WHERE movie_id = ?";
//            try(PreparedStatement statement = connection.prepareStatement(sql)){
//                statement.setDouble(1, movie.getAverageMark());
//                statement.setInt(2, movie.getId());
//                statement.executeUpdate();
//            } catch (SQLException e) {
//                throw new DatabaseQueryException(e.getMessage());
//            }
//        } catch (SQLException e) {
//            throw new DatabaseQueryException(e.getMessage());
//        }
    }
}
