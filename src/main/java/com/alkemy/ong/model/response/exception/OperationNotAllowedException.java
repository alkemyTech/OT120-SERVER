package com.alkemy.ong.model.response.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class OperationNotAllowedException extends RuntimeException {

  public OperationNotAllowedException(String message) {
    super(message);
  }

}
