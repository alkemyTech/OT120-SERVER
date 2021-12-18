package com.alkemy.ong.controller;

import com.alkemy.ong.exception.OperationNotAllowedException;
import com.alkemy.ong.model.entity.News;
import com.alkemy.ong.service.NewsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/news")
public class NewsController {

    @Autowired
    NewsServiceImpl NewServiceImpl;

    @PostMapping(value= "")
    public ResponseEntity<News> postNews (@RequestBody News news) throws OperationNotAllowedException {
        return ResponseEntity.status(HttpStatus.OK).body(NewServiceImpl.postNews(news.getName(), news.getContent(), news.getImage(), news.getCategory()));
         }
}
