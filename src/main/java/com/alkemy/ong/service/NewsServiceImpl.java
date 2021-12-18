package com.alkemy.ong.service;

import com.alkemy.ong.exception.OperationNotAllowedException;
import com.alkemy.ong.model.entity.News;
import com.alkemy.ong.model.response.NewsResponseDto;
import com.alkemy.ong.repository.INewsRepository;
import com.alkemy.ong.service.abstraction.INewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NewsServiceImpl implements INewsService{

    private static final String NEWS_NOT_FOUND_MESSAGE = "News not found.";

    @Autowired
    INewsRepository newsRepository;


    @Override
    public NewsResponseDto findNewsById(Long id) throws OperationNotAllowedException {
        News news = newsRepository.findById(id)
                .orElseThrow(() -> new OperationNotAllowedException(NEWS_NOT_FOUND_MESSAGE));
        return NewsResponseDto.newsToDto(news);
    }



}
