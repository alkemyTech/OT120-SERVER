package com.alkemy.ong.mapper;

import com.alkemy.ong.dto.Base64MultipartFileDto;
import com.alkemy.ong.dto.SlideDto;
import com.alkemy.ong.dto.SlideDtoOrganization;
import com.alkemy.ong.model.entity.Slide;
import com.alkemy.ong.service.abstraction.IOrganizationService;
import com.alkemy.ong.service.abstraction.IUploadImageService;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

import com.alkemy.ong.dto.SlideRequestDto;
import com.alkemy.ong.dto.SlideResponseDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityNotFoundException;

@Component
public class SlideMapper {

    @Autowired
    IOrganizationService organizationService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    IUploadImageService uploadImageService;

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

    public Slide slideDTO2Entity(SlideDto slideDto, int order) throws EntityNotFoundException, IOException {
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

    private String encodeImage(String imgUrl) throws EntityNotFoundException, IOException {

        byte[] encoded = Base64.getDecoder().decode(imgUrl);
        Base64MultipartFileDto file = new Base64MultipartFileDto(encoded);
        file.setName(UUID.randomUUID().toString() + ".jpg");

        return uploadImageService.uploadImage(file);
    }

}
