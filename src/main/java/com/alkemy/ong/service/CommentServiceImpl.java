package com.alkemy.ong.service;

import com.alkemy.ong.config.ApplicationRole;
import com.alkemy.ong.dto.CommentsBodyDto;
import com.alkemy.ong.enums.exception.OperationNotAllowedException;
import com.alkemy.ong.mapper.CommentsMapper;
import com.alkemy.ong.dto.CommentDto;
import com.alkemy.ong.exception.NotEmptyException;
import com.alkemy.ong.mapper.CommentMapper;
import com.alkemy.ong.model.entity.Comment;
import com.alkemy.ong.model.entity.Role;
import com.alkemy.ong.model.entity.User;
import com.alkemy.ong.repository.ICommentRepository;
import com.alkemy.ong.service.abstraction.ICommentService;
import com.alkemy.ong.service.abstraction.IGetUserService;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements ICommentService {

    private static final String COMMENT_NOT_FOUND_MESSAGE = "Comment not found.";
    private static final String USER_IS_NOT_ABLE_TO_DELETE_COMMENT_MESSAGE = "User is not able to delete comment.";
    private static final String COMMENT_NOT_EMPTY_MESSAGE = "Do not forget to leave a comment about the Post!";
    private static final String NEWSID_NOT_EMPTY_MESSAGE = "You must choose a post to leave a comment!";
    private static final String USERID_NOT_EMPTY_MESSAGE = "You must log in before leaving a comment!";

    @Autowired
    private CommentsMapper commentsMapper;

    @Autowired
    private ICommentRepository commentRepository;

    @Autowired
    private IGetUserService getUserService;

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public CommentDto save(CommentDto commentDto){
        if(commentDto.getBody().trim().isEmpty()){
          throw new NotEmptyException(COMMENT_NOT_EMPTY_MESSAGE);
        }
        if(commentDto.getNewsId() == null){
          throw new NotEmptyException(NEWSID_NOT_EMPTY_MESSAGE);
        }
        if(commentDto.getUserId() == null){
          throw new NotEmptyException(USERID_NOT_EMPTY_MESSAGE);
        }
        Comment commentEntity = commentMapper.commentDto2Entity(commentDto);
        Comment commentSaved = commentRepository.save(commentEntity);
        CommentDto result = commentMapper.commentEntity2Dto(commentSaved);
        return result;
    }

    @Override
    public void delete(Long id, String authorizationHeader) throws OperationNotAllowedException {
        Comment comment = getComment(id);

        throwExceptionIfOperationIsNotAllowed(
            getUserService.getBy(authorizationHeader),
            comment,
            USER_IS_NOT_ABLE_TO_DELETE_COMMENT_MESSAGE);

          commentRepository.delete(comment);
    }

    private boolean hasRole(String nameRole, List<Role> roles) {
        return roles.stream().anyMatch(role -> nameRole.equals(role.getName()));
    }

    private void throwExceptionIfOperationIsNotAllowed(User user, Comment comment, String message) {
        boolean isRoleAdmin = hasRole(ApplicationRole.ADMIN.getFullRoleName(), user.getRoles());
          if (!comment.getUserId().getId().equals(user.getId()) && !isRoleAdmin) {
              throw new OperationNotAllowedException(message);
          }
    }

  public List<Comment> getAllComments() {
    return commentRepository.findAll();
  }


  private Comment getComment(Long id) {
      Optional<Comment> commentOptional = commentRepository.findById(id);
          if (commentOptional.isEmpty()) {
              throw new EntityNotFoundException(COMMENT_NOT_FOUND_MESSAGE);
          }
          return commentOptional.get();
      }

    @Override
    public List<CommentsBodyDto> getCommentsBody() {
        List<Comment> entities = commentRepository.findAll();
        Collections.sort(entities, (o1, o2) -> o2.getTimestamp().compareTo(o1.getTimestamp()));
        return entities.stream()
                .map(entity -> commentsMapper.categoryToCategoryDto(entity))
                .collect(Collectors.toList());
    }
}
