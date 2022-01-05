package com.alkemy.ong.mapper;

import com.alkemy.ong.model.entity.News;
import com.alkemy.ong.dto.NewsDto;
import com.alkemy.ong.service.abstraction.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class NewsMapper {

    @Autowired
    ICategoryService categoryService;

    public News newsDtoToEntity(NewsDto dto) {
        News news = new News();
        news.setName(dto.getName());
        news.setContent(dto.getContent());
        news.setImage(dto.getImage());
        news.setTimestamp(new Timestamp(System.currentTimeMillis()));
        news.setCategory(categoryService.getCategory(dto.getCategory()));
        return news;
    }

    public NewsDto newsEntityToDto(News news) {
        NewsDto dto = new NewsDto();
        dto.setName(news.getName());
        dto.setContent(news.getContent());
        dto.setImage(news.getImage());
        dto.setCategory(news.getCategory().getId());
        return dto;
    }

    public NewsDto newsToDto(News news) {
        return NewsDto.builder()
                .name(news.getName())
                .content(news.getContent())
                .image(news.getImage())
                .category(news.getCategory().getId())
                .build();
    }

    public News updateValues(NewsDto dto, News entity) {
        entity.setName(dto.getName());
        entity.setContent(dto.getContent());
        entity.setImage(dto.getImage());
        entity.setCategory(categoryService.getCategory(dto.getCategory()));
        return entity;
    }
}



