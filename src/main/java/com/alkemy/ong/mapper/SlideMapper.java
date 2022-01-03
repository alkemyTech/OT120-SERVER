package com.alkemy.ong.mapper;

<<<<<<< HEAD
import com.alkemy.ong.dto.SlideDto;
import com.alkemy.ong.model.entity.Slide;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SlideMapper {

    public List<SlideDto> entity2DtoList(List<Slide> slides) {
        List<SlideDto> slidesDto = new ArrayList<>();

        for (Slide s : slides) {
            SlideDto dto = new SlideDto();
            dto.setImageUrl(s.getImageUrl());
            slidesDto.add(dto);
        }
        return slidesDto;
=======
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
>>>>>>> c08ee483849c29cef24dbaf657093e712912151c
    }
}
