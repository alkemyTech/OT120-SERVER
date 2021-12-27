package com.alkemy.ong.config.controller;

import com.alkemy.ong.dto.LoginRequestDto;
import com.alkemy.ong.dto.TokenDto;
import com.alkemy.ong.exception.InvalidCredentialsException;
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
    public ResponseEntity<TokenDto> login (@Valid @RequestBody LoginRequestDto userReq) throws InvalidCredentialsException, NotFoundException {
        return new ResponseEntity<TokenDto>(autoAuthenticationService.authenticateUser(userReq),HttpStatus.OK);
    }
}
