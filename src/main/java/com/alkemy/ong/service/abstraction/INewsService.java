package com.alkemy.ong.service.abstraction;

import com.alkemy.ong.exception.FieldInvalidException;
import com.alkemy.ong.exception.OperationNotAllowedException;
import com.alkemy.ong.dto.NewsDto;

public interface INewsService {

    NewsDto findNewsById(Long id) throws OperationNotAllowedException;

    NewsDto postNews(NewsDto newsDto) throws FieldInvalidException;

}
