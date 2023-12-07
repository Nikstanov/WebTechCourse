package org.education.service.impl;

import org.education.DAO.UserDAO;
import org.education.DAO.exception.DatabaseQueryException;
import org.education.beans.Review;
import org.education.beans.Role;
import org.education.beans.User;
import org.education.service.UserService;
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
        return userDAO.findAll();
    }

    @Override
    public Optional<User> getUserByUsername(String name) throws ServiceException {
        return Optional.ofNullable(userDAO.getUserByUsername(name));
    }

    @Override
    public Optional<User> getUserById(int id) throws ServiceException {
        return Optional.ofNullable(userDAO.getUserById(id));
    }

    @Override
    public void newUser(String email, String password, String username) throws ServiceException {
        User user;
        String str = passwordEncoder.encode(password);
        user = User.builder().token(generateNewToken())
                .login(email)
                .password(str)
                .username(username)
                .role(Role.ROLE_USER)
                .socialCredit(0)
                .build();
        userDAO.save(user);
    }

    @Override
    public void updateRate() throws ServiceException {
        List<User> users = userDAO.findAll();
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
            userDAO.save(user);
        }
    }

    @Override
    public void deleteUser(int id) throws ServiceException {
        userDAO.deleteAllById(id);
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
            userDAO.save(user);
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
