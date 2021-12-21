package com.alkemy.ong.mapper;

import com.alkemy.ong.dto.UsersResponseDto;
import com.alkemy.ong.model.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    @Autowired
    private ModelMapper modelMapper;

    public UsersResponseDto usersDtoResponse (User entity){
        return modelMapper.map(entity, UsersResponseDto.class);
    }
}
