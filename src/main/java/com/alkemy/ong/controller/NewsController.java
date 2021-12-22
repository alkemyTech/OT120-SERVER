package com.alkemy.ong.controller;

import com.alkemy.ong.exception.FieldInvalidException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.alkemy.ong.dto.NewsDto;
import com.alkemy.ong.service.abstraction.INewsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
