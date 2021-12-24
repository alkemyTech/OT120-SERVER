package com.alkemy.ong.service.abstraction;

import com.alkemy.ong.dto.UserDtoRequest;
import com.alkemy.ong.dto.UserDtoResponse;

import javax.persistence.EntityNotFoundException;

public interface IUserService {

    UserDtoResponse save(UserDtoRequest userRequestDto);

    void delete(Long id) throws EntityNotFoundException;

    UserDtoResponse update(Long id, UserDtoRequest userDTORequest);
    
}
