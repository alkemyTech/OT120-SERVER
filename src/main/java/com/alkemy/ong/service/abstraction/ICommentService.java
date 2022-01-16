package com.alkemy.ong.service.abstraction;

import java.util.List;

import com.alkemy.ong.dto.CommentDto;
import com.alkemy.ong.dto.CommentsBodyDto;
import com.alkemy.ong.exception.OperationNotAllowedException;

public interface ICommentService {

  CommentDto save(CommentDto commentDto);

  void delete(Long id, String authorizationHeader) throws OperationNotAllowedException;

  List<CommentsBodyDto> getCommentsBody();
  
  void update(Long id, CommentDto dto, String authorizationHeader) throws OperationNotAllowedException;

}
