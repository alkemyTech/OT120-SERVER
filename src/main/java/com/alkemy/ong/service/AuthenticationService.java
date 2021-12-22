package com.alkemy.ong.service;


import com.alkemy.ong.common.JwtUtil;
import com.alkemy.ong.entity.User;
import com.alkemy.ong.model.request.LoginRequest;
import com.alkemy.ong.model.response.TokenDto;
import com.alkemy.ong.exception.InvalidCredentialsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {


    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserServiceImpl userService;


    public TokenDto authenticateUser (LoginRequest userReq) throws InvalidCredentialsException {
        User user = userService.getUserByEmail(userReq.getEmail());

        if(user == null){
            throw new UsernameNotFoundException("The user is not registered.");
        }
        else if(!passwordEncoder.matches(userReq.getPassword(),user.getPassword())){
            throw new InvalidCredentialsException("The data entered are invalid.");
        }

        final String jwt = jwtUtil.generateToken(user);

        return  new TokenDto(jwt);
    }
}
