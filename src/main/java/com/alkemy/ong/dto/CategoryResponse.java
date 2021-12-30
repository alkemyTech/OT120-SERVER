package com.alkemy.ong.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryResponse {

    public long id;
    public String name;
    public String description;
    public String image;
}
