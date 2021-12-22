package com.alkemy.ong.service.abstraction;


import com.alkemy.ong.dto.UserDto;

public interface IUserService {

    //Update
    UserDto update(Long id, UserDto userDTO);

}

