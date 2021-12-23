package com.alkemy.ong.controller;

import com.alkemy.ong.dto.UsersResponseDto;
import com.alkemy.ong.service.abstraction.IGetAllUsers;
import com.alkemy.ong.dto.UserDto;
import com.alkemy.ong.exception.InvalidCredentialsException;
import com.alkemy.ong.dto.LoginRequestDto;
import com.alkemy.ong.dto.TokenDto;
import com.alkemy.ong.service.AuthenticationService;
import com.alkemy.ong.service.abstraction.IUserService;
import com.fasterxml.jackson.databind.introspect.TypeResolutionContext.Empty;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

  @Autowired
  public IUserService userService;

  @Autowired
  AuthenticationService autoAuthenticationService;

  public IGetAllUsers getAllUsers;


  @PostMapping("/auth/register")

 public ResponseEntity<UserDto> postUser(@RequestBody UserDto userDto) throws InvalidCredentialsException {

    UserDto newUser = userService.save(userDto);

    LoginRequestDto loginRequest = new LoginRequestDto();
    loginRequest.setEmail(newUser.getEmail());
    loginRequest.setPassword(newUser.getPassword());
    TokenDto tokenDto = autoAuthenticationService.authenticateUser(loginRequest);
    newUser.setToken(tokenDto.getToken());
    return new ResponseEntity<>(newUser, HttpStatus.OK);
  }

  @DeleteMapping(value = "/users/{id}")
  public ResponseEntity<Empty> delete(@PathVariable Long id) throws EntityNotFoundException {
    userService.delete(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @GetMapping(value="/users")
  public ResponseEntity<List<UsersResponseDto>>getAllUsers(){
    return new ResponseEntity<List<UsersResponseDto>>(getAllUsers.getAllUsers(),HttpStatus.OK);
  }
}
