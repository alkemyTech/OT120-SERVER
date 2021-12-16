package com.alkemy.ong.controller;

import com.alkemy.ong.dto.NewsDTO;
import com.alkemy.ong.service.abstraction.INewsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/news")
public class NewsController {

    @Autowired
    INewsService newsService;

    ModelMapper modelMapper = new ModelMapper();

    @GetMapping("/{id}")
    public ResponseEntity<?> getNewsById(@PathVariable("id") Long id) {
        NewsDTO newsDTO = this.newsService.getNewsDTO(id);
        if (this.newsService.existId(id)) {
            NewsDTO response= new NewsDTO();
            modelMapper.map(newsDTO, response);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("news " + id + " no encontrada!");
        }
    }
}
