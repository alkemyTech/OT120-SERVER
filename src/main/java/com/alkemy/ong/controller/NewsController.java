package com.alkemy.ong.controller;

import com.alkemy.ong.exception.FieldInvalidException;
import com.fasterxml.jackson.databind.introspect.TypeResolutionContext.Empty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.alkemy.ong.dto.NewsDto;
import com.alkemy.ong.service.abstraction.INewsService;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

@RestController
@RequestMapping("/news")
public class NewsController {

    @Autowired
    INewsService newsService;

    @PostMapping(value = "")
    public ResponseEntity<NewsDto> postNews(@Valid @RequestBody NewsDto newsDto) throws FieldInvalidException {
        return ResponseEntity.status(HttpStatus.CREATED).body(newsService.postNews(newsDto));
    }


    @GetMapping("/{id}")
    public ResponseEntity<NewsDto> getNewsById(@PathVariable Long id) {
        return ResponseEntity.ok(newsService.findNewsById(id));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Empty> delete(@PathVariable long id) throws EntityNotFoundException {
        newsService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
