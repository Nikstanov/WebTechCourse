package org.education.utills;

import org.education.beans.dto.SignUpDTO;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {

    private final UserDetailsService userDetailsService;

    public UserValidator(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return SignUpDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        SignUpDTO sign = (SignUpDTO) target;
        try{
            userDetailsService.loadUserByUsername(sign.getEmail());
        } catch (UsernameNotFoundException ignored){
            return;
        }
        errors.rejectValue("email", "", "User with this email already exists");

    }
}
