package com.alkemy.ong.service.abstraction;

import com.alkemy.ong.enums.exception.OperationNotAllowedException;

public interface ICommentsService {

  void delete(Long id, String authorizationHeader) throws OperationNotAllowedException;

}
