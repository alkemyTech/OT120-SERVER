package com.alkemy.ong.service.abstraction;

import com.alkemy.ong.model.entity.User;
import com.alkemy.ong.model.response.UserDtoResponse;
import javassist.NotFoundException;

public interface IGetUserService {

  User getBy(String authorizationHeader);
  UserDtoResponse getMe(String jwt) throws NotFoundException;
  UserDtoResponse entityToDto(User user);
}
