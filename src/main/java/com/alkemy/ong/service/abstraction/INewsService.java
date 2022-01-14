package com.alkemy.ong.service.abstraction;

import com.alkemy.ong.dto.PageDto;
import com.alkemy.ong.enums.exception.FieldInvalidException;
import com.alkemy.ong.enums.exception.NotFoundExceptions;
import com.alkemy.ong.enums.exception.OperationNotAllowedException;
import com.alkemy.ong.dto.NewsDto;
<<<<<<< HEAD

import javax.persistence.EntityNotFoundException;
=======
import com.alkemy.ong.model.entity.Comment;
import com.alkemy.ong.model.entity.News;

import javax.persistence.EntityNotFoundException;
import java.lang.reflect.Field;
import java.util.List;
>>>>>>> e5b5056411ba6d34a6d92fb17f98e08caa9ffa8c

public interface INewsService {

    News getNews(Long id);

    NewsDto findNewsById(Long id) throws OperationNotAllowedException;

    NewsDto postNews(NewsDto newsDto) throws FieldInvalidException;

    NewsDto updateNews (NewsDto newsDto, Long id);

    void delete(Long id) throws EntityNotFoundException;

<<<<<<< HEAD
    PageDto<NewsDto> getPage(Integer page, Integer sizePage, String sortBy) throws NotFoundExceptions;
=======
    List<Comment> commentPerNews(Long id);
>>>>>>> e5b5056411ba6d34a6d92fb17f98e08caa9ffa8c
}
