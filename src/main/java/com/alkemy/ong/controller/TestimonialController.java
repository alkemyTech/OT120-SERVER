package com.alkemy.ong.controller;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.alkemy.ong.dto.TestimonialRequestDto;
import com.alkemy.ong.model.entity.Testimonial;
import com.alkemy.ong.service.abstraction.ITestimonialService;
import com.fasterxml.jackson.databind.introspect.TypeResolutionContext.Empty;

@RestController
public class TestimonialController {

  @Autowired
  private ITestimonialService testimonialService;

  @DeleteMapping(value = "/testimonials/{id}")
  public ResponseEntity<Empty> delete(@PathVariable Long id) throws EntityNotFoundException {
    testimonialService.delete(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @PostMapping(value = "/testimonials")
  public ResponseEntity<Testimonial> newtestimonials(@Valid @RequestBody TestimonialRequestDto testimonialRequest) {
    return new ResponseEntity<Testimonial>(testimonialService.save(testimonialRequest), HttpStatus.CREATED);
  }
  
  @PutMapping(value = "/testimonials/{id}")
  public ResponseEntity<TestimonialRequestDto> updateTestimonial(@Valid @RequestBody TestimonialRequestDto testimonialRequest, @PathVariable Long id) {
    TestimonialRequestDto dto = testimonialService.update(testimonialRequest, id);
	return new ResponseEntity<TestimonialRequestDto>(dto, HttpStatus.CREATED);
  }
   
}
