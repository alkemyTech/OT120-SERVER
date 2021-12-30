package com.alkemy.ong.controller;

import com.alkemy.ong.dto.TestimonialRequestDto;
import com.alkemy.ong.model.entity.Testimonial;
import com.alkemy.ong.service.abstraction.ITestimonialService;
import com.fasterxml.jackson.databind.introspect.TypeResolutionContext.Empty;
import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TestimonialController {

  @Autowired
  private ITestimonialService testimonialService;

  @DeleteMapping(value = "/testimonials/{id}")
  public ResponseEntity<Empty> delete(@PathVariable Long id) throws EntityNotFoundException {
    testimonialService.delete(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @PostMapping (value = "/testimonials")
  public ResponseEntity<Testimonial> newtestimonials(@Valid @RequestBody TestimonialRequestDto testimonialRequest) {
    return new ResponseEntity<Testimonial>(testimonialService.save(testimonialRequest), HttpStatus.CREATED);
  }
}
