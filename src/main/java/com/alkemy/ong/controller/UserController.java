package com.alkemy.ong.controller;

import com.alkemy.ong.exception.InvalidCredentialsException;
import com.alkemy.ong.model.entity.User;
import com.alkemy.ong.model.request.LoginRequest;
import com.alkemy.ong.model.request.RegistrationRequest;
import com.alkemy.ong.model.response.RegistrationResponse;
import com.alkemy.ong.model.response.TokenDto;
import com.alkemy.ong.service.AuthenticationService;
import com.alkemy.ong.service.UserServiceImpl;
import com.alkemy.ong.service.abstraction.IDeleteUserService;
import com.fasterxml.jackson.databind.introspect.TypeResolutionContext.Empty;
import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

  @Autowired
  public IDeleteUserService deleteUserService;

  @Autowired
  public UserServiceImpl userService;

  @Autowired
  AuthenticationService autoAuthenticationService;

  @PostMapping("/auth/register")
  public ResponseEntity<TokenDto> postRegisterUser(@RequestBody @Valid RegistrationRequest req) throws InvalidCredentialsException {

    RegistrationResponse r = new RegistrationResponse();

    User user = userService.postUser(req);

    r.setUsername(user.getUsername());
    r.setFirstName(user.getFirstName());
    r.setLastName(user.getLastName());

    LoginRequest loginRequest = new LoginRequest();
    loginRequest.setEmail(user.getEmail());
    loginRequest.setPassword(user.getPassword());

    return new ResponseEntity<TokenDto>(autoAuthenticationService.authenticateUser(loginRequest),HttpStatus.OK);
  }

  @DeleteMapping(value = "/users/{id}")
  public ResponseEntity<Empty> delete(@PathVariable Long id) throws EntityNotFoundException {
    deleteUserService.delete(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

}
