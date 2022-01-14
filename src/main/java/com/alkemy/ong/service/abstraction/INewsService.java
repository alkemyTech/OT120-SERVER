package com.alkemy.ong.service.abstraction;

import com.alkemy.ong.dto.PageDto;
import com.alkemy.ong.enums.exception.FieldInvalidException;
import com.alkemy.ong.enums.exception.NotFoundExceptions;
import com.alkemy.ong.enums.exception.OperationNotAllowedException;
import com.alkemy.ong.dto.NewsDto;

import javax.persistence.EntityNotFoundException;

public interface INewsService {

    NewsDto findNewsById(Long id) throws OperationNotAllowedException;

    NewsDto postNews(NewsDto newsDto) throws FieldInvalidException;

    NewsDto updateNews (NewsDto newsDto, Long id);

    void delete(Long id) throws EntityNotFoundException;

    PageDto<NewsDto> getPage(Integer page, Integer sizePage, String sortBy) throws NotFoundExceptions;
}
