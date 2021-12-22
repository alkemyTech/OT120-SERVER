package com.alkemy.ong.model.response;

import com.alkemy.ong.model.entity.Category;
import com.alkemy.ong.model.entity.News;
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

    private Long idNews;

    private String name;

    private String content;

    private String image;

    private Timestamp timestamp;

    private Category category;

    public static NewsResponseDto newsToDto(News news) {
        return NewsResponseDto.builder()
                .idNews(news.getId())
                .name(news.getName())
                .content(news.getContent())
                .image(news.getImage())
                .timestamp(news.getTimestamp())
                .category(news.getCategory())
                .build();
    }

}
