package com.alkemy.ong.dto;

import com.alkemy.ong.model.entity.News;
import com.alkemy.ong.model.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Setter
@Getter
@NoArgsConstructor
@Component
public class CommentDto {

    private static final String COMMENT_NOT_BLANK_MESSAGE = "Do not forget to leave a comment about the Post!";
    private static final String NEWSID_NOT_NULL_MESSAGE = "You must choose a post to leave a comment!";
    private static final String USERID_NOT_NULL_MESSAGE = "You must log in before leaving a comment!";

    private Long id;

    @NotBlank(message = COMMENT_NOT_BLANK_MESSAGE)
    private String body;

    @NotNull(message = USERID_NOT_NULL_MESSAGE)
    private Long userId;

    @NotNull(message = NEWSID_NOT_NULL_MESSAGE)
    private Long newsId;

    private Timestamp timestamp;
}
