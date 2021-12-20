package com.alkemy.ong.controller;

import com.alkemy.ong.dto.UserDTO;
import com.alkemy.ong.service.abstraction.IDeleteUserService;
import com.alkemy.ong.service.abstraction.UserService;
import com.fasterxml.jackson.databind.introspect.TypeResolutionContext.Empty;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class UserController {

  private UserService userService;

  @Autowired
  public IDeleteUserService deleteUserService;

  @Autowired
  public UserController(UserService userService){
    this.userService = userService;
  }

  //Update
  @PutMapping("/{id}")
  public ResponseEntity<UserDTO> update(@PathVariable Long id, @RequestBody UserDTO userDTO){
    UserDTO userResult = this.userService.update(id, userDTO);
    return ResponseEntity.ok().body(userResult);
  }

  @DeleteMapping(value = "/users/{id}")
  public ResponseEntity<Empty> delete(@PathVariable Long id) throws EntityNotFoundException {
    deleteUserService.delete(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

}
