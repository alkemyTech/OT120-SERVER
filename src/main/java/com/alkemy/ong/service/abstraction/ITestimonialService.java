package com.alkemy.ong.service.abstraction;

import com.alkemy.ong.dto.TestimonialRequestDto;
import com.alkemy.ong.model.entity.Testimonial;

import javax.persistence.EntityNotFoundException;

public interface ITestimonialService {

  void delete(Long id) throws EntityNotFoundException;

   Testimonial save(TestimonialRequestDto testimonialRequest);
}
