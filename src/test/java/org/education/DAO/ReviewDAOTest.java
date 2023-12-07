package org.education.DAO;

import org.education.beans.Movie;
import org.education.beans.Review;
import org.education.beans.Role;
import org.education.beans.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ReviewDAOTest {

    @Autowired
    TestEntityManager testEntityManager;

    @Autowired
    UserDAO userDAO;

    @Autowired
    MovieDAO movieDAO;

    @Autowired
    ReviewDAO reviewDAO;

    @Test
    void getAverageMarkTest(){
        User user1 = User.builder().role(Role.ROLE_USER).login("some").username("Username").password("some").build();
        user1 = testEntityManager.persist(user1);
        User user2 = User.builder().role(Role.ROLE_USER).login("someAnother").username("Username1").password("some1").build();
        user2 = testEntityManager.persist(user2);
        User user3 = User.builder().role(Role.ROLE_USER).login("Some3").username("Username1").password("some1").build();
        user3 = testEntityManager.persist(user3);
        Movie movie = Movie.builder().name("some").shortDescription("some").description("some").author("some").build();
        movie = testEntityManager.persist(movie);
        Review review1 = Review.builder().user(user1).movie(movie).mark(3).review("some").build();
        Review review2 = Review.builder().user(user2).movie(movie).mark(4).review("some").build();
        Review review3 = Review.builder().user(user3).movie(movie).mark(2).review("some").build();
        testEntityManager.persist(review1);
        testEntityManager.persist(review2);
        testEntityManager.persist(review3);
        testEntityManager.flush();
        Assertions.assertEquals(3,reviewDAO.getAverageMark(movie.getId()));
    }
}
