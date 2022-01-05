package com.alkemy.ong.mapper;

import com.alkemy.ong.dto.SlideDto;
import com.alkemy.ong.model.entity.Slide;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

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

    public SlideResponseDto slideEntity2Dto(Slide entity) {
        return modelMapper.map(entity, SlideResponseDto.class);
    }

    public Slide slideReqDto2Entity(SlideRequestDto slideReqDto) {
        return modelMapper.map(slideReqDto, Slide.class);
    }


    public List<SlideRequestDto> entity2DtoList(List<Slide> slides) {

        List<SlideRequestDto> slidesDtoList = new ArrayList<>();

        for (Slide s : slides) {
            SlideRequestDto dto = new SlideRequestDto();
            dto.setImageUrl(s.getImageUrl());
            dto.setOrder(s.getOrder());
            slidesDtoList.add(dto);
        }
        return slidesDtoList;
    }
}
