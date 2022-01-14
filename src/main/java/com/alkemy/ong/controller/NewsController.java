package com.alkemy.ong.controller;

import com.alkemy.ong.dto.PageDto;
import com.alkemy.ong.enums.exception.FieldInvalidException;
import com.alkemy.ong.enums.exception.NotFoundExceptions;
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

    public NewsController() {
    }

    @PostMapping("")
    public ResponseEntity<NewsDto> postNews(@Valid @RequestBody NewsDto newsDto) throws FieldInvalidException {
        return ResponseEntity.status(HttpStatus.CREATED).body(newsService.postNews(newsDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<NewsDto> getNewsById(@PathVariable Long id) {
        return ResponseEntity.ok(newsService.findNewsById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<NewsDto> updateNews(@Valid @RequestBody NewsDto newsDto, @PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(newsService.updateNews(newsDto, id));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Empty> delete(@PathVariable long id) throws EntityNotFoundException {
        newsService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/page")
    public ResponseEntity<PageDto<NewsDto>> getPage(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer sizePage,
            @RequestParam(defaultValue = "id") String sortBy) throws NotFoundExceptions {
        return new ResponseEntity<>(newsService.getPage(page, sizePage, sortBy), HttpStatus.OK);
    }
}
