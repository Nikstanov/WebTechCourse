package org.education.service.impl;

import com.google.common.hash.Hashing;
import org.education.DAO.DAOFactory;
import org.education.DAO.UserDAO;
import org.education.DAO.exception.DatabaseQueryException;
import org.education.beans.Role;
import org.education.beans.User;
import org.education.beans.UserPrincipal;
import org.education.service.UserService;
import org.education.service.exception.AlreadyExistException;
import org.education.service.exception.ServiceException;
import org.education.service.exception.UnknownUserException;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class UserServiceImpl implements UserService {
    UserDAO userDAO = DAOFactory.getInstance().getUserDAO();

    @Override
    public List<User> getUsers(int pageInd) throws ServiceException {
        return null;
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
        return Optional.empty();
    }

    @Override
    public UserPrincipal newUser(String email, String password, String username) throws ServiceException, AlreadyExistException {
        User user;
        try {
            if(userDAO.getUserByEmail(email).isEmpty()){
                String str = Hashing.sha256().hashString(password, StandardCharsets.UTF_8).toString();
                user = User.builder().token(generateNewToken())
                        .login(email)
                        .password(str)
                        .username(username)
                        .banned(false)
                        .role(Role.USER)
                        .socialCredit(0)
                        .build();
                userDAO.saveUser(user);
            }
            else{
                throw new AlreadyExistException("User already exist");
            }

        } catch (DatabaseQueryException e) {
            throw new ServiceException(e.getMessage());
        }
        return new UserPrincipal(user);
    }

    @Override
    public UserPrincipal authenticate(String login, String password) throws ServiceException, UnknownUserException {
        try {
            Optional<User> optionalUser = userDAO.getUserByEmail(login);
            if(optionalUser.isEmpty()){
                throw new UnknownUserException("Unknown user with login = " + login);
            }
            String str = Hashing.sha256().hashString(password,StandardCharsets.UTF_8).toString();
            if(optionalUser.get().getPassword().equals(str)){
                User user = optionalUser.get();
                user.setToken(generateNewToken());
                userDAO.updateUser(user);
                return new UserPrincipal(user);
            }
            return null;
        } catch (DatabaseQueryException e) {
            throw new ServiceException(e.getMessage());
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
