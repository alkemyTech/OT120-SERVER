package com.alkemy.ong.controller;

import com.alkemy.ong.model.entity.User;
import com.alkemy.ong.model.request.RegistrationRequest;
import com.alkemy.ong.model.response.RegistrationResponse;
import com.alkemy.ong.service.UserServiceImpl;
import com.alkemy.ong.service.abstraction.IDeleteUserService;
import com.fasterxml.jackson.databind.introspect.TypeResolutionContext.Empty;
import javax.persistence.EntityNotFoundException;
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

  @PostMapping("/auth/register")
  public ResponseEntity<RegistrationResponse> postRegisterUser(@RequestBody RegistrationRequest req) {

    RegistrationResponse r = new RegistrationResponse();

    User user = userService.postUser(req.firstName, req.lastName, req.email, req.password);

    r.username =user.getUsername();

    return ResponseEntity.ok(r);
  }

  @DeleteMapping(value = "/users/{id}")
  public ResponseEntity<Empty> delete(@PathVariable Long id) throws EntityNotFoundException {
    deleteUserService.delete(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

}
