package com.alkemy.ong.mapper;

import com.alkemy.ong.dto.PageDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.alkemy.ong.dto.TestimonialRequestDto;
import com.alkemy.ong.model.entity.Testimonial;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    private List<TestimonialRequestDto> toTestimonialDtoList(Page<Testimonial> testimonialPage) {

        List<TestimonialRequestDto> testimonialDtos = new ArrayList<>();

        if (testimonialPage.hasContent()) {

            testimonialDtos = testimonialPage.stream().map(testimonial -> {
                return new TestimonialRequestDto(testimonial.getName(), testimonial.getImage(),testimonial.getContent());
            }).collect(Collectors.toList());
        }

        return testimonialDtos;
    }

    public PageDto<TestimonialRequestDto> toPageDto(Page<Testimonial>testimonialPage,Integer pageNumber,Integer totalPages) {

        PageDto<TestimonialRequestDto> pageDto = new PageDto<>();

        pageDto.setTotalPages(totalPages);

        if (testimonialPage.hasNext()) {

            pageDto.setNextPage("/alltestimonials?page=" + (pageNumber + 1));
        }

        if (testimonialPage.hasPrevious()) {

            pageDto.setPreviousPage("/alltestimonials?page=" + (pageNumber - 1));
        }

        pageDto.setList(toTestimonialDtoList(testimonialPage));
        return pageDto;
    }
}
