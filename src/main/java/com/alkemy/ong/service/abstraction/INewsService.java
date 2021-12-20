package com.alkemy.ong.service.abstraction;

import com.alkemy.ong.exception.FieldInvalidException;
import com.alkemy.ong.exception.OperationNotAllowedException;
import com.alkemy.ong.dto.NewsResponseDto;

public interface INewsService {

    NewsResponseDto findNewsById(Long id) throws OperationNotAllowedException;

    NewsResponseDto postNews(NewsResponseDto newsDto) throws FieldInvalidException;

}
