package com.alkemy.ong.service.abstraction;

import com.alkemy.ong.dto.PageDto;
import com.alkemy.ong.exception.FieldInvalidException;
import com.alkemy.ong.exception.NotFoundExceptions;
import com.alkemy.ong.exception.OperationNotAllowedException;
import com.alkemy.ong.dto.NewsDto;

import javax.persistence.EntityNotFoundException;

import com.alkemy.ong.model.entity.Comment;
import com.alkemy.ong.model.entity.News;

import java.util.List;

public interface INewsService {

    News getNews(Long id);

    NewsDto findNewsById(Long id) throws OperationNotAllowedException;

    NewsDto postNews(NewsDto newsDto) throws FieldInvalidException;

    NewsDto updateNews (NewsDto newsDto, Long id);

    void delete(Long id) throws EntityNotFoundException;

    PageDto<NewsDto> getPage(Integer page, Integer sizePage, String sortBy) throws NotFoundExceptions;

    List<Comment> commentPerNews(Long id);

}
