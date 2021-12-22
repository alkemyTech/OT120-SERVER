package com.alkemy.ong.service;

import com.alkemy.ong.config.modelmapper.NewsMapper;
import com.alkemy.ong.model.entity.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alkemy.ong.repository.INewsRepository;
import com.alkemy.ong.dto.NewsResponseDto;
import com.alkemy.ong.service.abstraction.INewsService;
import javax.persistence.EntityNotFoundException;

@Service
public class NewsServiceImpl implements INewsService {

    private static final String NEWS_NOT_FOUND_MESSAGE = "News not found.";

    @Autowired
    INewsRepository newsRepository;

    @Autowired
    NewsMapper newsMapper;

    @Override
    public NewsResponseDto findNewsById(Long id) throws EntityNotFoundException {
        News news = newsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(NEWS_NOT_FOUND_MESSAGE));
        return newsMapper.newsToDto(news);
    }

    @Override
    public NewsResponseDto postNews(NewsResponseDto newsDto) {
        News newsEntity = newsMapper.newsDtoToEntity(newsDto);
        News saved = newsRepository.save(newsEntity);
        NewsResponseDto result = newsMapper.newsEntityToDto(saved);
        return result;
    }
}