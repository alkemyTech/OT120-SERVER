package com.alkemy.ong.controller;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import com.alkemy.ong.dto.PageDto;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.alkemy.ong.dto.TestimonialRequestDto;
import com.alkemy.ong.model.entity.Testimonial;
import com.alkemy.ong.service.abstraction.ITestimonialService;
import com.fasterxml.jackson.databind.introspect.TypeResolutionContext.Empty;

@RestController
@RequestMapping("/testimonials")
public class TestimonialController {

  @Autowired
  private ITestimonialService testimonialService;

  @DeleteMapping(value = "/{id}")
  public ResponseEntity<Empty> delete(@PathVariable Long id) throws EntityNotFoundException {
    testimonialService.delete(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @PostMapping
  public ResponseEntity<Testimonial> newtestimonials(@Valid @RequestBody TestimonialRequestDto testimonialRequest) {
    return new ResponseEntity<Testimonial>(testimonialService.save(testimonialRequest), HttpStatus.CREATED);
  }
  
  @PutMapping(value = "/{id}")
  public ResponseEntity<TestimonialRequestDto> updateTestimonial(@Valid @RequestBody TestimonialRequestDto testimonialRequest, @PathVariable Long id) {
    TestimonialRequestDto dto = testimonialService.update(testimonialRequest, id);
	return new ResponseEntity<TestimonialRequestDto>(dto, HttpStatus.OK);
  }

  @GetMapping(value="/alltestimonials")
  public ResponseEntity<PageDto<TestimonialRequestDto>> getPage(@RequestParam (defaultValue = "0") Integer page,
                                                                @RequestParam (defaultValue = "10") Integer sizePage ,
                                                                @RequestParam (defaultValue = "id") String sortBy) throws NotFoundException {

    return new ResponseEntity<>(testimonialService.getPage(page,sizePage,sortBy), HttpStatus.OK);
  }
}
