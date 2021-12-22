package com.alkemy.ong.config.modelmapper;

import com.alkemy.ong.model.entity.News;
import com.alkemy.ong.dto.NewsResponseDto;
import com.alkemy.ong.service.abstraction.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NewsMapper {

    @Autowired
    ICategoryService categoryService;

    public News newsDtoToEntity(NewsResponseDto dto) {
        News news = new News();
        news.setName(dto.getName());
        news.setContent(dto.getContent());
        news.setImage(dto.getImage());
        news.setCategory(categoryService.getCategory(dto.getCategory()));
        return news;
    }

    public NewsResponseDto newsEntityToDto(News news) {
        NewsResponseDto dto = new NewsResponseDto();
        dto.setName(news.getName());
        dto.setContent(news.getContent());
        dto.setImage(news.getImage());
        dto.setCategory(news.getCategory().getId());
        return dto;
    }

    public NewsResponseDto newsToDto(News news) {
        return NewsResponseDto.builder()
                .name(news.getName())
                .content(news.getContent())
                .image(news.getImage())
                .timestamp(news.getTimestamp())
                .category(news.getCategory().getId())
                .build();
    }
}



