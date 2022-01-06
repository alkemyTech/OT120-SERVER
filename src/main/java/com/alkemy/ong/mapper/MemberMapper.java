package com.alkemy.ong.mapper;

import com.alkemy.ong.dto.MemberDto;
import com.alkemy.ong.model.entity.Member;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberMapper {

    @Autowired
    private ModelMapper modelMapper;

    public MemberDto memberToDto (Member entity){
        return modelMapper.map(entity, MemberDto.class);
    }
}