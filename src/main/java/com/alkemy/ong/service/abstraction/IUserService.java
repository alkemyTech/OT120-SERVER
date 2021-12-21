package com.alkemy.ong.service.abstraction;

import com.alkemy.ong.dto.UserDto;

public interface IUserService {

    UserDto save(UserDto userRequestDto);
}
