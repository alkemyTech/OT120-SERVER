package com.alkemy.ong.controller;

import com.alkemy.ong.dto.UserDto;
import com.alkemy.ong.service.abstraction.IDeleteUserService;
import com.alkemy.ong.service.abstraction.IUserService;
import com.fasterxml.jackson.databind.introspect.TypeResolutionContext.Empty;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class UserController {
  @Autowired
  private IUserService userService;

  @Autowired
  public IDeleteUserService deleteUserService;


  //Update
  @PutMapping("/{id}")
  public ResponseEntity<UserDto> update(@PathVariable Long id, @RequestBody UserDto userDto){
    UserDto userResult = this.userService.update(id, userDto);
    return ResponseEntity.ok().body(userResult);
  }

  @DeleteMapping(value = "/users/{id}")
  public ResponseEntity<Empty> delete(@PathVariable Long id) throws EntityNotFoundException {
    deleteUserService.delete(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

}
