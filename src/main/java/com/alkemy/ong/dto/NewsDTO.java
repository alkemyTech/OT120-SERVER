package com.alkemy.ong.dto;

import com.alkemy.ong.model.entity.Category;
import java.security.Timestamp;

public class NewsDTO {

    private Long id;
    private String name;
    private String content;
    private String image;
    private Category category;
    private Timestamp timestamp;

}
