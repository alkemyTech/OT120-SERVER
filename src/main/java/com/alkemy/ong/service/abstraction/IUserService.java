package com.alkemy.ong.service.abstraction;

import com.alkemy.ong.dto.UserDtoRequest;
import com.alkemy.ong.dto.UserDtoResponse;
import com.alkemy.ong.exception.NotFoundExceptions;
import com.alkemy.ong.model.entity.User;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

public interface IUserService {

    @Transactional
    User findByEmail(String email);

    UserDtoResponse save(UserDtoRequest userRequestDto);

    void delete(Long id) throws EntityNotFoundException;

    UserDtoResponse update(Long id, UserDtoRequest userDTORequest);

    User getInfoUser() throws NotFoundExceptions;

}
