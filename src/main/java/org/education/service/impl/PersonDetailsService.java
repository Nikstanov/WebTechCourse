package org.education.service.impl;

import org.education.DAO.UserDAO;
import org.education.DAO.exception.DatabaseQueryException;
import org.education.beans.User;
import org.education.beans.security.PersonDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonDetailsService implements UserDetailsService {

    private final UserDAO userDAO;

    public PersonDetailsService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = Optional.ofNullable(userDAO.getUserByLogin(username));
        if(user.isEmpty()){
            throw new UsernameNotFoundException("User not found");
        }
        return new PersonDetails(user.get());
    }
}
