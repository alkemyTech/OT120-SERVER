package com.alkemy.ong.controller;

import com.alkemy.ong.dto.SlideRequestDto;
import com.alkemy.ong.dto.SlideResponseDto;
import com.alkemy.ong.service.SlideServiceImpl;
import com.alkemy.ong.service.abstraction.ISlideService;
import com.fasterxml.jackson.databind.introspect.TypeResolutionContext.Empty;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class SlideController {

  @Autowired
  private ISlideService slideService;

  @Autowired
  private SlideServiceImpl slideServiceImpl;

  @DeleteMapping(value = "/slides/{id}")
  public ResponseEntity<Empty> delete(@PathVariable("id") long id) throws EntityNotFoundException {
    slideService.delete(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @PutMapping("/slides/{id}")
  public ResponseEntity<SlideResponseDto> update(@PathVariable long id, @RequestBody SlideRequestDto slideRequestDto)
          throws EntityNotFoundException {
    SlideResponseDto updatedSlide = slideServiceImpl.update(id, slideRequestDto);
    return ResponseEntity.ok().body(updatedSlide);
  }

  @GetMapping("/Slides/{id}")
  public ResponseEntity<SlideResponseDto> getOne(@PathVariable long id) throws EntityNotFoundException{
    return ResponseEntity.status(HttpStatus.OK).body(slideService.getById(id));
  }

}

