package com.alkemy.ong.controller;

import com.alkemy.ong.dto.NewsDTO;
import com.alkemy.ong.service.abstraction.INewsService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/id")
    public ResponseEntity<NewsDTO> getNewsById(@PathVariable Long id){
        NewsDTO newsDTO = newsService.findNewsById(id);
        return ResponseEntity.ok().body(newsDTO);
    }

}
