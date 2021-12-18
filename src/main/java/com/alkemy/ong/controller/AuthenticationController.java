package com.alkemy.ong.controller;

import com.alkemy.ong.exception.InvalidCredentialsException;
import com.alkemy.ong.model.entity.User;
import com.alkemy.ong.model.request.LoginRequest;
import com.alkemy.ong.service.AuthenticationService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class AuthenticationController {
    @Autowired
    AuthenticationService autoAuthenticationService;

    @PostMapping(value = "/auth/login")
    public ResponseEntity<User> login (@Valid @RequestBody LoginRequest userReq) throws InvalidCredentialsException, NotFoundException {
        User user =null;
        return new ResponseEntity<User>(user ,HttpStatus.OK);
    }
}
