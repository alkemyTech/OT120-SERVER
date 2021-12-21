package com.alkemy.ong.mapper;

import com.alkemy.ong.dto.UsersDtoResponse;
import com.alkemy.ong.model.entity.Category;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class UserMapper {

    @Autowired
    private ModelMapper modelMapper;

    public UsersDtoResponse usersDtoResponse (Category entity){
        return modelMapper.map(entity, UsersDtoResponse.class);
    }
}
