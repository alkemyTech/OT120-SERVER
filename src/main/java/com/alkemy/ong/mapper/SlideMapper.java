package com.alkemy.ong.mapper;

import com.alkemy.ong.dto.SlideDto;
import com.alkemy.ong.model.entity.Slide;
import com.alkemy.ong.service.abstraction.IOrganizationService;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Comparator;
import java.util.List;

import com.alkemy.ong.dto.SlideRequestDto;
import com.alkemy.ong.dto.SlideResponseDto;
import com.alkemy.ong.model.entity.Slide;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;

@Component
public class SlideMapper {

    @Autowired
    IOrganizationService organizationService;

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

    public Slide slideDTO2Entity(SlideDto slideDto, int order) throws EntityNotFoundException {
        Slide slide = new Slide();
        slide.setImageUrl(encodeImage(slideDto.getImageUrl()));
        slide.setText(slideDto.getText());
        slide.setOrder(order);
        slide.setOrganizationId(organizationService.getOrganizationId(slideDto.getOrganizationId()));

        return slide;
    }

    public SlideDto slideEntity2DTO(Slide slide) {
        SlideDto slideDTO = new SlideDto();
        slideDTO.setId(slide.getId());
        slideDTO.setImageUrl(slide.getImageUrl());
        slideDTO.setText(slide.getText());
        slideDTO.setOrder(slide.getOrder());
        slideDTO.setOrganizationId(slide.getOrganizationId().getId());

        return slideDTO;
    }

    private String encodeImage(String imgUrl) throws EntityNotFoundException {

        Base64.Encoder encoder = Base64.getEncoder();
        byte[] encoded = encoder.encode(imgUrl.getBytes(StandardCharsets.UTF_8));
        return new String(encoded);
    }

    private String decodeImage(String imgUrl) throws EntityNotFoundException {

        Base64.Decoder decoder = Base64.getDecoder();
        byte[] decoded = decoder.decode(imgUrl.getBytes(StandardCharsets.UTF_8));
        return new String(decoded);
    }
}
