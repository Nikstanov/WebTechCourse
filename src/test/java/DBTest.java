import org.education.DAO.MovieDAO;
import org.education.DAO.UserDAO;
import org.education.DAO.exception.DBException;
import org.education.beans.Movie;
import org.education.beans.User;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

public class DBTest {
    @Test
    public void getUsersTest() throws DBException {
        UserDAO userDAO = new UserDAO();
        Optional<User> userOptional = userDAO.getUserByName("n.stahnov@gmail.com");
        User user = userOptional.orElseThrow();
    }

    @Test
    public void getAllMovies() throws DBException {
        MovieDAO movieDAO = new MovieDAO();
        List<Movie> movieList = movieDAO.getMovies();
    }
}
