package com.alkemy.ong.mapper;

import com.alkemy.ong.dto.CommentDto;
import com.alkemy.ong.model.entity.Comment;
import com.alkemy.ong.service.abstraction.INewsService;
import com.alkemy.ong.service.abstraction.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class CommentMapper {

    @Autowired
    INewsService newsService;

    @Autowired
    IUserService userService;

    public Comment commentDto2Entity(CommentDto commentDto){
        Comment commentEntity = new Comment();
        commentEntity.setBody(commentDto.getBody());
        commentEntity.setUserId(userService.getUser(commentDto.getUserId()));
        commentEntity.setNewsId(newsService.getNews(commentDto.getNewsId()));
        commentEntity.setTimestamp(new Timestamp(System.currentTimeMillis()));
        return commentEntity;
    }
    public CommentDto commentEntity2Dto(Comment commentEntity){
        CommentDto commentDto = new CommentDto();
        commentDto.setId(commentEntity.getId());
        commentDto.setBody(commentEntity.getBody());
        commentDto.setUserId(commentEntity.getUserId().getId());
        commentDto.setNewsId(commentEntity.getNewsId().getId());
        commentDto.setTimestamp(commentEntity.getTimestamp());
        return commentDto;
    }

}
