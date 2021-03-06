package com.alkemy.ong.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
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
