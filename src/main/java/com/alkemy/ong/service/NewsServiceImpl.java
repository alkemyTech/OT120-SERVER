package com.alkemy.ong.service;

import com.alkemy.ong.config.modelmapper.NewsMapper;
import com.alkemy.ong.model.entity.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alkemy.ong.repository.INewsRepository;
import com.alkemy.ong.dto.NewsDto;
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
    public NewsDto findNewsById(Long id) throws EntityNotFoundException {
        News news = newsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(NEWS_NOT_FOUND_MESSAGE));
        return newsMapper.newsToDto(news);
    }

    @Override
    public NewsDto postNews(NewsDto newsDto) {
        News newsEntity = newsMapper.newsDtoToEntity(newsDto);
        News saved = newsRepository.save(newsEntity);
        NewsDto result = newsMapper.newsEntityToDto(saved);
        return result;
    }
}