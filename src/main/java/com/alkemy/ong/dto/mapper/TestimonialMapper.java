package com.alkemy.ong.dto.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alkemy.ong.dto.TestimonialRequestDto;
import com.alkemy.ong.model.entity.Testimonial;

@Component
public class TestimonialMapper {

    @Autowired
    ModelMapper modelMapper;

    public Testimonial dto2Entity(TestimonialRequestDto testimonialRequest) {

        Testimonial testimonial = modelMapper.map(testimonialRequest, Testimonial.class);

        return testimonial;
    }
    
    public Testimonial refreshValues(TestimonialRequestDto dto, Testimonial entity) {
    	modelMapper.map(dto, entity);
        return entity;
    }

    public TestimonialRequestDto entity2Dto(Testimonial entity) {
    	TestimonialRequestDto dto = modelMapper.map(entity, TestimonialRequestDto.class);
    	return dto;
    }

    
}
