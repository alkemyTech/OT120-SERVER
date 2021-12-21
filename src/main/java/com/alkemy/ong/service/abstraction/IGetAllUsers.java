package com.alkemy.ong.service.abstraction;

import com.alkemy.ong.dto.UsersResponseDto;

import java.util.List;

public interface IGetAllUsers {
    public List<UsersResponseDto> getAllUsers();
}
