package com.alkemy.ong.mapper;

import com.alkemy.ong.model.entity.Category;
import com.alkemy.ong.dto.CategoryRequest;
import com.alkemy.ong.dto.CategoryResponse;
import com.alkemy.ong.dto.CategoryDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    @Autowired
    private ModelMapper modelMapper;

    public CategoryDto categoryToCategoryDto(Category entity) {
        return modelMapper.map(entity, CategoryDto.class);
    }

    public Category categoryRequest2Entity(CategoryRequest categoryDto) {
        return modelMapper.map(categoryDto, Category.class);
    }

    public CategoryResponse category2Dto(Category entity) {
        return modelMapper.map(entity, CategoryResponse.class);
    }

    public Category categoryDtotoCategory(CategoryDto dto) {
        Category category = new Category();
        category.setName(dto.getName());
        return category;
    }
}