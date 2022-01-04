package com.alkemy.ong.mapper;

import com.alkemy.ong.dto.SlideRequestDto;
import com.alkemy.ong.dto.SlideResponseDto;
import com.alkemy.ong.model.entity.Slide;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SlideMapper {

    @Autowired
    private ModelMapper modelMapper;

    public SlideResponseDto slideEntity2Dto (Slide entity) {
        return modelMapper.map(entity, SlideResponseDto.class);
    }

    public Slide slideReqDto2Entity (SlideRequestDto slideReqDto) {
        return modelMapper.map(slideReqDto, Slide.class);
    }
}
