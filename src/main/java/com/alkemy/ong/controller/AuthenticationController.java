package com.alkemy.ong.controller;

import com.alkemy.ong.dto.LoginRequestDto;
import com.alkemy.ong.dto.TokenDto;
import com.alkemy.ong.exception.InvalidCredentialsException;
import com.alkemy.ong.exception.NotFoundExceptions;
import com.alkemy.ong.model.entity.User;
import com.alkemy.ong.service.AuthenticationService;
import com.alkemy.ong.service.abstraction.IUserService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController

public class AuthenticationController {

    @Autowired
    AuthenticationService autoAuthenticationService;

    @Autowired
    IUserService userService;

    private User getUser;

    @PostMapping(value = "/auth/login")
    public ResponseEntity<TokenDto> login(@Valid @RequestBody LoginRequestDto userReq) throws InvalidCredentialsException, NotFoundException {
        return new ResponseEntity<TokenDto>(autoAuthenticationService.authenticateUser(userReq), HttpStatus.OK);
    }

    @GetMapping("/auth/me")
    public ResponseEntity<User> userLogged() throws NotFoundExceptions {
        return new ResponseEntity<>(userService.getInfoUser(), HttpStatus.OK);
    }

    /**
     @GetMapping(path = "/auth/mi")
     ResponseEntity<?> getUserInfo(@Parameter(hidden = true) @AuthenticationPrincipal UserDetailsImpl user) {
     return ResponseEntity.ok(user.getUser());
     }
     **/

}