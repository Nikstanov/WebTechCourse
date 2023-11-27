package org.education.service.impl;

import org.education.DAO.UserDAO;
import org.education.DAO.exception.DatabaseQueryException;
import org.education.beans.Review;
import org.education.beans.Role;
import org.education.beans.User;
import org.education.service.UserService;
import org.education.service.exception.AlreadyExistException;
import org.education.service.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Component
public class UserServiceImpl implements UserService {
    private final UserDAO userDAO;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserDAO userDAO, PasswordEncoder passwordEncoder) {
        this.userDAO = userDAO;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<User> getUsers() throws ServiceException {
        try {
            return userDAO.getUsers();
        } catch (DatabaseQueryException e) {
            throw new ServiceException();
        }
    }

    @Override
    public Optional<User> getUserByUsername(String name) throws ServiceException {
        try {
            return userDAO.getUserByUsername(name);
        } catch (DatabaseQueryException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public Optional<User> getUserById(int id) throws ServiceException {
        try {
            return userDAO.getUserById(id);
        } catch (DatabaseQueryException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public void newUser(String email, String password, String username) throws ServiceException {
        User user;
        try {
            String str = passwordEncoder.encode(password);
            user = User.builder().token(generateNewToken())
                    .login(email)
                    .password(str)
                    .username(username)
                    .role(Role.ROLE_USER)
                    .socialCredit(0)
                    .build();
            userDAO.saveUser(user);
        } catch (DatabaseQueryException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public void updateRate() throws ServiceException {
        try {
            List<User> users = userDAO.getUsers();
            for(User user : users){
                int socialCredit = 0;
                List<Review> reviews = user.getReviewList();
                for (Review review : reviews){
                    double movie_mark = review.getMovie().getAverageMark();
                    if(Math.abs(review.getMark() - movie_mark) > 1){
                        socialCredit -= 10;
                    }
                    else {
                        socialCredit += 10;
                    }
                }
                user.setSocialCredit(socialCredit);
                userDAO.saveUser(user);
            }
        } catch (DatabaseQueryException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public void deleteUser(int id) throws ServiceException {
        try {
            userDAO.deleteUser(id);
        } catch (DatabaseQueryException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public void changeStatus(int id) throws ServiceException {
        Optional<User> userOptional = getUserById(id);
        if(userOptional.isPresent()){
            User user = userOptional.get();
            if(user.getRole() != Role.ROLE_BANNED){
                user.setRole(Role.ROLE_BANNED);
            }else {
                user.setRole(Role.ROLE_USER);
            }
            try {
                userDAO.saveUser(user);
            } catch (DatabaseQueryException e) {
                throw new ServiceException(e.getMessage());
            }
        }
    }

    private static final SecureRandom secureRandom = new SecureRandom(); //threadsafe
    private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder(); //threadsafe

    private static String generateNewToken() {
        byte[] randomBytes = new byte[24];
        secureRandom.nextBytes(randomBytes);
        return base64Encoder.encodeToString(randomBytes);
    }

}
