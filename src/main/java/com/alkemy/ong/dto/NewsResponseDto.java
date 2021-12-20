package com.alkemy.ong.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Timestamp;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewsResponseDto {

    private Long id;

    private String name;

    private String content;

    private String image;

    private Timestamp timestamp;

    private Long category;


   /* public static NewsResponseDto newsToDto(News news) {
        return NewsResponseDto.builder()
                .id(news.getId())
                .name(news.getName())
                .content(news.getContent())
                .image(news.getImage())
                .timestamp(news.getTimestamp())
                .category(news.getCategory())
                .build();
    }*/

}
