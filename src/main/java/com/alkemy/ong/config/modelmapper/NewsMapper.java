package com.alkemy.ong.config.modelmapper;

import com.alkemy.ong.model.entity.News;
import com.alkemy.ong.dto.NewsResponseDto;

public class NewsMapper {


    public News newsDtoToEntity(NewsResponseDto dto) {
        News news = new News();
        news.setName(dto.getName());
        news.setContent(dto.getContent());
        news.setImage(dto.getImage());
        //news.setCategory(categoryService.getCategory(dto.getCategory())); /*aun no actualizo para tener esa funcion de categoria*/
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

    public  NewsResponseDto newsToDto(News news) {
        return NewsResponseDto.builder()
                .id(news.getId())
                .name(news.getName())
                .content(news.getContent())
                .image(news.getImage())
                .timestamp(news.getTimestamp())
                .category(news.getCategory().getId())
                .build();
    }
}



