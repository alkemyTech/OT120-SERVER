package com.alkemy.ong.controller;

import com.alkemy.ong.dto.SlideDto;
import com.alkemy.ong.service.abstraction.ISlideService;
import com.fasterxml.jackson.databind.introspect.TypeResolutionContext.Empty;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("slides")
public class SlideController {

  @Autowired
  private ISlideService SlideService;

  @DeleteMapping(value = "/slides/{id}")
  public ResponseEntity<Empty> delete(@PathVariable("id") long id) throws EntityNotFoundException {
    SlideService.delete(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
  @GetMapping("/")
  public ResponseEntity<List<SlideDto>> listAll(){
    return ResponseEntity.ok(SlideService.listAll()) ;
  }

}

