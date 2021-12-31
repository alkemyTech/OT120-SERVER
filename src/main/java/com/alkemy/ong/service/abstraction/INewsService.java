package com.alkemy.ong.service.abstraction;

import com.alkemy.ong.exception.FieldInvalidException;
import com.alkemy.ong.exception.OperationNotAllowedException;
import com.alkemy.ong.dto.NewsDto;
import com.alkemy.ong.model.entity.News;

import javax.persistence.EntityNotFoundException;
import java.lang.reflect.Field;

public interface INewsService {

    NewsDto findNewsById(Long id) throws OperationNotAllowedException;

    NewsDto postNews(NewsDto newsDto) throws FieldInvalidException;

<<<<<<< HEAD
    NewsDto updateNews (NewsDto newsDto, Long id);

=======
    void delete(Long id) throws EntityNotFoundException;
    ;
>>>>>>> 3c40176f36fc1b45c5f9c616069d5dffba4b32ef
}
