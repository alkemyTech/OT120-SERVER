package com.alkemy.ong.service;

import com.alkemy.ong.exception.InvalidCredentialsException;
import com.alkemy.ong.model.entity.User;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class AuthenticationService {
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public User authenticate(String email, String rawPassword) throws NotFoundException, InvalidCredentialsException {
        User user = userService.getUserByEmail(email);

        if(user == null){
            throw new UsernameNotFoundException("The user is not registered.");
        }
        else if(!passwordEncoder.matches(rawPassword,user.getPassword())){
            throw new InvalidCredentialsException("The data entered are invalid.");
        }
        return user;
    }
}
