package com.alkemy.ong.service.abstraction;

import com.alkemy.ong.dto.PageDto;
import com.alkemy.ong.dto.TestimonialRequestDto;
import com.alkemy.ong.model.entity.Testimonial;
import javassist.NotFoundException;

import javax.persistence.EntityNotFoundException;

public interface ITestimonialService {

  void delete(Long id) throws EntityNotFoundException;

   Testimonial save(TestimonialRequestDto testimonialRequest);

   TestimonialRequestDto update(TestimonialRequestDto testimonialRequest, Long id);

   PageDto<TestimonialRequestDto> getPage(Integer page, Integer sizePage, String sortBy) throws NotFoundException;

}
