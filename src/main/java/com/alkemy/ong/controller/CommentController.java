package com.alkemy.ong.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.alkemy.ong.dto.CommentsBodyDto;
import com.alkemy.ong.exception.OperationNotAllowedException;
import com.alkemy.ong.service.CommentServiceImpl;
import com.alkemy.ong.service.abstraction.ICommentsService;
import com.fasterxml.jackson.databind.introspect.TypeResolutionContext.Empty;

@RestController
public class CommentController {

  @Autowired
  private ICommentsService CommentsService;
  
  @Autowired 
  private CommentServiceImpl commentsService;

  @DeleteMapping(value = "/comments/{id}")
  public ResponseEntity<Empty> delete(@PathVariable("id") long id,
      @RequestHeader(value = "Authorization") String authorizationHeader)
      throws OperationNotAllowedException {
    CommentsService.delete(id, authorizationHeader);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
  
  @GetMapping(value = "/comments")
  public ResponseEntity<List<CommentsBodyDto>> getCommentBody(){
      return ResponseEntity.status(HttpStatus.OK).body(commentsService.getCommentsBody());
  }
  
  @PutMapping(value = "/comments/{id}")
  public ResponseEntity<Empty> update(
		  @PathVariable("id") long id,
		  @RequestBody CommentsBodyDto dto,
		  @RequestHeader(value = "Authorization") String authorizationHeader)
      throws OperationNotAllowedException {
    CommentsService.update(id, dto, authorizationHeader);
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
