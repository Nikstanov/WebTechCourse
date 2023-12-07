package org.education.DAO;

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
public class UserDAOTest {

    @Autowired
    TestEntityManager testEntityManager;

    @Autowired
    UserDAO userDAO;

    @Test
    void getUserByIdTest(){
        User user = User.builder().role(Role.ROLE_USER).login("some").username("Username").password("some").build();
        user = testEntityManager.persist(user);
        testEntityManager.flush();
        Assertions.assertEquals(user, userDAO.getUserById(user.getId()));
    }
}
