package com.alkemy.ong.service.abstraction;

import com.alkemy.ong.dto.CommentDto;
import com.alkemy.ong.exception.OperationNotAllowedException;

public interface ICommentService {

  CommentDto save(CommentDto commentDto);

  void delete(Long id, String authorizationHeader) throws OperationNotAllowedException;

}
