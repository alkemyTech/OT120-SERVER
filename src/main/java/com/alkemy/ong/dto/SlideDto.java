package com.alkemy.ong.dto;

import com.alkemy.ong.model.entity.Organization;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SlideDto {
    private long id;
    private String imageUrl;
    private int order;
    private String text;
    private long organizationId;
}
