package com.alkemy.ong.mapper;

import com.alkemy.ong.dto.TestimonialRequestDto;
import com.alkemy.ong.model.entity.Testimonial;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TestimonialMapper {

    @Autowired
    ModelMapper modelMapper;

    public Testimonial dto2Entity(TestimonialRequestDto testimonialRequest) {

        Testimonial testimonial = modelMapper.map(testimonialRequest, Testimonial.class);

        return testimonial;
    }


}
