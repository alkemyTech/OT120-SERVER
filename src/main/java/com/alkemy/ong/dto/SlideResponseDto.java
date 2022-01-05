package com.alkemy.ong.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SlideResponseDto {

    public long id;
    public String imageUrl;
    public String text;
    public int order;
    public long organizationId;
}
