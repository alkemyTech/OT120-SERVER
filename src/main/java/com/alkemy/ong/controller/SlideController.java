package com.alkemy.ong.controller;

<<<<<<< HEAD
import com.alkemy.ong.dto.SlideDto;
=======
import com.alkemy.ong.dto.SlideRequestDto;
import com.alkemy.ong.dto.SlideResponseDto;
import com.alkemy.ong.service.SlideServiceImpl;
>>>>>>> c08ee483849c29cef24dbaf657093e712912151c
import com.alkemy.ong.service.abstraction.ISlideService;
import com.fasterxml.jackson.databind.introspect.TypeResolutionContext.Empty;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
<<<<<<< HEAD

import java.util.List;
=======
>>>>>>> c08ee483849c29cef24dbaf657093e712912151c

@RestController
@RequestMapping("slides")
public class SlideController {

  @Autowired
  private ISlideService SlideService;

  @Autowired
  private SlideServiceImpl slideService;

  @DeleteMapping(value = "/slides/{id}")
  public ResponseEntity<Empty> delete(@PathVariable("id") long id) throws EntityNotFoundException {
    SlideService.delete(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
  @GetMapping("/")
  public ResponseEntity<List<SlideDto>> listAll(){
    return ResponseEntity.ok(SlideService.listAll()) ;
  }

  @PutMapping("/slides/{id}")
  public ResponseEntity<SlideResponseDto> update(@PathVariable long id, @RequestBody SlideRequestDto slideRequestDto)
          throws EntityNotFoundException {
    SlideResponseDto updatedSlide = slideService.update(id, slideRequestDto);
    return ResponseEntity.ok().body(updatedSlide);
  }

}

