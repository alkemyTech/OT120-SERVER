package com.alkemy.ong.mapper;

import com.alkemy.ong.dto.SlideDtoOrganization;
import com.alkemy.ong.model.entity.Slide;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import com.alkemy.ong.dto.SlideRequestDto;
import com.alkemy.ong.dto.SlideResponseDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

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
            dto.setText(s.getText());
            dto.setOrder(s.getOrder());
            slidesDtoList.add(dto);
        }
        return slidesDtoList;
    }

    public List<Slide> slideDtoList2EntityList(List<SlideRequestDto> slideReqDto) {

        List<Slide> slideList = new ArrayList<>();

        for (SlideRequestDto requestDto : slideReqDto) {
            Slide slide = new Slide();
            slide.setImageUrl((requestDto.getImageUrl()));
            slide.setText(requestDto.getText());
            slide.setOrder(requestDto.getOrder());
            slideList.add(slide);
        }
        return slideList;
    }

    public List<SlideDtoOrganization> slideList2DtoList(List<Slide> slides) {

        List<SlideDtoOrganization> slidesDtoList = new ArrayList<>();

        for (Slide s : slides) {
            SlideDtoOrganization dto = new SlideDtoOrganization();
            dto.setImageUrl(s.getImageUrl());
            dto.setText(s.getText());
            dto.setOrder(s.getOrder());
            slidesDtoList.add(dto);
        }
        return slidesDtoList;
    }
}
