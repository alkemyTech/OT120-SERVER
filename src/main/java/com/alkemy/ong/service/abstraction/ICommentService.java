package com.alkemy.ong.service.abstraction;

import com.alkemy.ong.dto.CommentDto;
import com.alkemy.ong.dto.CommentsBodyDto;
import com.alkemy.ong.exception.OperationNotAllowedException;

import java.util.List;

public interface ICommentService {

  CommentDto save(CommentDto commentDto);

  void delete(Long id, String authorizationHeader) throws OperationNotAllowedException;

  List<CommentsBodyDto> getCommentsBody();

}
