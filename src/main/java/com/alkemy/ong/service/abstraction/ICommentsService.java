package com.alkemy.ong.service.abstraction;

import com.alkemy.ong.dto.CommentsBodyDto;
import com.alkemy.ong.exception.OperationNotAllowedException;

public interface ICommentsService {

  void delete(Long id, String authorizationHeader) throws OperationNotAllowedException;
  
  void update(Long id, CommentsBodyDto dto, String authorizationHeader) throws OperationNotAllowedException;

}
