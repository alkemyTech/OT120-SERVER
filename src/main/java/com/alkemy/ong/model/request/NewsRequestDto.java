package com.alkemy.ong.model.request;

import com.alkemy.ong.model.entity.Category;
import com.alkemy.ong.model.entity.News;
import lombok.AllArgsConstructor;


import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.sql.Timestamp;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewsRequestDto {

    @NotEmpty(message = "Cannot be null or empty.")
    private String name;

    @NotEmpty(message = "Cannot be null or empty.")
    private String content;

    @NotEmpty(message = "Cannot be null or empty.")
    private String image;

    private Timestamp timestamp;

    private Category category;

    public static News dtoToNews(NewsRequestDto dto){
        return News.builder()
                .name(dto.getName())
                .content(dto.getContent())
                .image(dto.getImage())
                .timestamp(dto.getTimestamp())
                .category(dto.getCategory()).build();
    }
}
