package com.alkemy.ong.model.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;

@Getter
@Setter
public class ActivityDto {

    private long id;

    private static final String NAME_NOT_BLANK_MESSAGE = "The Name' field cannot be empty!";
    private static final String CONTENT_NOT_BLANK_MESSAGE = "The content' field cannot be empty!";

    @NotBlank(message = NAME_NOT_BLANK_MESSAGE)
    private String name;
    @NotBlank(message = CONTENT_NOT_BLANK_MESSAGE)
    private String content;
    private String image;
    private Timestamp timestamp;

}
