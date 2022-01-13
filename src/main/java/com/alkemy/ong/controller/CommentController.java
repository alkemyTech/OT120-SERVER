package com.alkemy.ong.controller;

import com.alkemy.ong.dto.CommentDto;
import com.alkemy.ong.dto.CommentsBodyDto;
import com.alkemy.ong.exception.OperationNotAllowedException;
import com.alkemy.ong.service.abstraction.ICommentService;
import com.fasterxml.jackson.databind.introspect.TypeResolutionContext.Empty;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/comments")
public class CommentController {

  @Autowired
  private ICommentService commentsService;

  @PostMapping
  public ResponseEntity<CommentDto> save(@Valid @RequestBody CommentDto commentDto, BindingResult errors) {
      if (errors.hasFieldErrors()) {
          StringBuilder stringBuilder = new StringBuilder();
          List<ObjectError> errorList = errors.getAllErrors();
          for (ObjectError error : errorList) {
              stringBuilder.append(error.getDefaultMessage());
          }
      }
      CommentDto commentSaved = commentsService.save(commentDto);
      return ResponseEntity.status(HttpStatus.CREATED).body(commentSaved);
  }



  @DeleteMapping(value = "/comments/{id}")
  public ResponseEntity<Empty> delete(@PathVariable("id") long id,
      @RequestHeader(value = "Authorization") String authorizationHeader)
      throws OperationNotAllowedException {
      commentsService.delete(id, authorizationHeader);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
  
  @GetMapping(value = "/comments")
  public ResponseEntity<List<CommentsBodyDto>> getCommentBody(){
      return ResponseEntity.status(HttpStatus.OK).body(commentsService.getCommentsBody());
  }
}
