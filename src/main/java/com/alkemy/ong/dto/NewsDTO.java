package com.alkemy.ong.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NewsDTO {


    private Long id;

    private String name;

    private String content;

    private String image;

    //private Long categoryId;
}
