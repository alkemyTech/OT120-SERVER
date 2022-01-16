package com.alkemy.ong.model.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class SlideDtoGet {

    private String imageUrl;
    private String text;
    private int order;

    private Long organizationId;

}
