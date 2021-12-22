package com.alkemy.ong.service.abstraction;

import com.alkemy.ong.dto.UserDto;

import javax.persistence.EntityNotFoundException;

public interface IUserService {

    UserDto save(UserDto userRequestDto);

    void delete(Long id) throws EntityNotFoundException;

}
