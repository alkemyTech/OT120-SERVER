package com.alkemy.ong.controller;

<<<<<<< HEAD
import com.alkemy.ong.model.entity.User;
import com.alkemy.ong.model.request.RegistrationRequest;
import com.alkemy.ong.model.response.RegistrationResponse;
import com.alkemy.ong.model.response.UserDtoResponse;
import com.alkemy.ong.service.UserServiceImpl;
import com.alkemy.ong.service.abstraction.IDeleteUserService;
import com.alkemy.ong.service.abstraction.IGetUserService;
=======
import com.alkemy.ong.dto.UserDto;
import com.alkemy.ong.service.abstraction.IUserService;
>>>>>>> 927862f5d456e34a70b0e31f5aab0e9cfb399de3
import com.fasterxml.jackson.databind.introspect.TypeResolutionContext.Empty;

import javax.persistence.EntityNotFoundException;
<<<<<<< HEAD
import javax.validation.Valid;

import javassist.NotFoundException;
=======
>>>>>>> 927862f5d456e34a70b0e31f5aab0e9cfb399de3
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

<<<<<<< HEAD
    @Autowired
    public IDeleteUserService deleteUserService;

    @Autowired
    public UserServiceImpl userService;

    @Autowired
    IGetUserService getUserService;

    @PostMapping("/auth/register")
    public ResponseEntity<RegistrationResponse> postRegisterUser(@RequestBody @Valid RegistrationRequest req) {

        RegistrationResponse r = new RegistrationResponse();

        User user = userService.postUser(req);

        r.setUsername(user.getUsername());
        r.setFirstName(user.getFirstName());
        r.setLastName(user.getLastName());

        return new ResponseEntity<>(r, HttpStatus.OK);
    }

    @DeleteMapping(value = "/users/{id}")
    public ResponseEntity<Empty> delete(@PathVariable Long id) throws EntityNotFoundException {
        deleteUserService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @GetMapping("/auth/me")
    public ResponseEntity<UserDtoResponse> getMe(@RequestHeader("authorization") String jwt) throws NotFoundException {
        return new ResponseEntity<>(getUserService.getMe(jwt), HttpStatus.OK);
    }
=======
  @Autowired
  public IUserService userService;

  @PostMapping("/auth/register")
  public ResponseEntity<UserDto> postUser(@RequestBody UserDto userDto) {
    UserDto newUser = userService.save(userDto);
    return new ResponseEntity<>(newUser, HttpStatus.OK);
  }

  @DeleteMapping(value = "/users/{id}")
  public ResponseEntity<Empty> delete(@PathVariable Long id) throws EntityNotFoundException {
    userService.delete(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
>>>>>>> 927862f5d456e34a70b0e31f5aab0e9cfb399de3

}
