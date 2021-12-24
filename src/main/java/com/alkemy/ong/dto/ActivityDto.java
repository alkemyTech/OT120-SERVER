package com.alkemy.ong.dto;

import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotEmpty;
import java.sql.Timestamp;

@Getter
@Setter
public class ActivityDto {

    private long id;
    @NotEmpty(message = "The Name' field cannot be empty!")
    private String name;
    @NotEmpty(message = "The content' field cannot be empty!")
    private String content;
    private String image;
    private Timestamp timestamp;

}
