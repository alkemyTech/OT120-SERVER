package com.alkemy.ong.dto.mapper;

import com.alkemy.ong.dto.PageDto;
import com.alkemy.ong.model.entity.News;
import com.alkemy.ong.dto.NewsDto;
import com.alkemy.ong.service.abstraction.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    private List<NewsDto> toNewsDtoList(Page<News> newsPage) {
        List<NewsDto> newsDtoList = new ArrayList<>();

        if (newsPage.hasContent()) {
            newsDtoList = newsPage.stream().map(news -> {
                return new NewsDto(news.getName(), news.getContent(), news.getImage(), news.getCategory().getId());
            }).collect(Collectors.toList());
        }
        return newsDtoList;
    }

    public PageDto<NewsDto> toPageDto(Page<News> newsPage, Integer pageNumber, Integer totalPages) {
        PageDto<NewsDto> pageDto = new PageDto<>();
        pageDto.setTotalPages(totalPages);

        if (newsPage.hasNext()) {
            pageDto.setNextPage("/page?page=" + (pageNumber + 1));
        }

        if (newsPage.hasPrevious()) {
            pageDto.setPreviousPage("/page?page=" + (pageNumber - 1));
        }
        pageDto.setList(toNewsDtoList((newsPage)));
        return pageDto;
    }
}



