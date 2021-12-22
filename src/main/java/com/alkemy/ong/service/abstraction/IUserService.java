package com.alkemy.ong.service.abstraction;

import com.alkemy.ong.dto.UserDto;
import com.alkemy.ong.model.entity.User;
import com.alkemy.ong.model.response.UserDTOResponse;
import javassist.NotFoundException;

import javax.persistence.EntityNotFoundException;

public interface IUserService {

    UserDto save(UserDto userRequestDto);

    void delete(Long id) throws EntityNotFoundException;

    UserDTOResponse getMe(String jwt) throws NotFoundException;

    UserDTOResponse entityToDto(User user);
}
