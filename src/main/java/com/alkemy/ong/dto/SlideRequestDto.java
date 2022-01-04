package com.alkemy.ong.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SlideRequestDto {

    public String imageUrl;
    public String text;
    public int order;
    public long organizationId;
}
