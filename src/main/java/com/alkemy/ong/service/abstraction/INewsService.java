package com.alkemy.ong.service.abstraction;

import com.alkemy.ong.exception.OperationNotAllowedException;
import com.alkemy.ong.model.response.NewsResponseDto;

public interface INewsService {

    NewsResponseDto findNewsById(Long id) throws OperationNotAllowedException;

}
