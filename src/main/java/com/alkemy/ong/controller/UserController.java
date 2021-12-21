package com.alkemy.ong.controller;

import com.alkemy.ong.dto.UsersResponseDto;
import com.alkemy.ong.model.entity.User;
import com.alkemy.ong.model.request.RegistrationRequest;
import com.alkemy.ong.model.response.RegistrationResponse;
import com.alkemy.ong.service.UserServiceImpl;
import com.alkemy.ong.service.abstraction.IDeleteUserService;
import com.alkemy.ong.service.abstraction.IGetAllUsers;
import com.fasterxml.jackson.databind.introspect.TypeResolutionContext.Empty;
import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

  @Autowired
  public IDeleteUserService deleteUserService;

  @Autowired
  public UserServiceImpl userService;

  @Autowired
  public IGetAllUsers getAllUsers;

  @PostMapping("/auth/register")
  public ResponseEntity<RegistrationResponse> postRegisterUser(@RequestBody @Valid RegistrationRequest req) {

    RegistrationResponse r = new RegistrationResponse();

    User user = userService.postUser(req);

    r.setUsername(user.getUsername());
    r.setFirstName(user.getFirstName());
    r.setLastName(user.getLastName());

    return new ResponseEntity<>(r , HttpStatus.OK);
  }

  @DeleteMapping(value = "/users/{id}")
  public ResponseEntity<Empty> delete(@PathVariable Long id) throws EntityNotFoundException {
    deleteUserService.delete(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @GetMapping(value="/users")
  public ResponseEntity<List<UsersResponseDto>>getAllUsers(){
    return new ResponseEntity<List<UsersResponseDto>>(getAllUsers.getAllUsers(),HttpStatus.OK);
  }
}
