package com.alkemy.ong.config.controller;

import com.alkemy.ong.dto.UserDtoRequest;
import com.alkemy.ong.dto.UserDtoResponse;
import com.alkemy.ong.dto.UsersResponseDto;
import com.alkemy.ong.service.abstraction.IGetAllUsers;
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
@RequestMapping("users")
public class UserController {
  @Autowired
  private IUserService userService;

  @Autowired
  AuthenticationService autoAuthenticationService;

  public IGetAllUsers getAllUsers;


  @PostMapping("/auth/register")
  public ResponseEntity<UserDtoResponse> postUser(@RequestBody UserDtoRequest userDtoRequest) throws InvalidCredentialsException {
    UserDtoResponse newUser = userService.save(userDtoRequest);
    LoginRequestDto loginRequest = new LoginRequestDto();
    loginRequest.setEmail(newUser.getEmail());
    loginRequest.setPassword(newUser.getPassword());
    TokenDto tokenDto = autoAuthenticationService.authenticateUser(loginRequest);
    newUser.setToken(tokenDto.getToken());
    return new ResponseEntity<>(newUser, HttpStatus.OK);
  }

  @PutMapping("/{id}")
  public ResponseEntity<UserDtoResponse> update(@PathVariable Long id, @RequestBody UserDtoRequest userDtoRequest){
    UserDtoResponse userResult = this.userService.update(id, userDtoRequest);
    return ResponseEntity.ok().body(userResult);
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
