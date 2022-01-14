package com.alkemy.ong.dto.mapper;

import com.alkemy.ong.model.entity.Category;
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

    public Category categoryDtoToCategory(CategoryDto categoryDto) {
        return modelMapper.map(categoryDto, Category.class);
    }

    public void categoryRefreshValue (Category entity, CategoryDto dto) {
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setImage(dto.getImage());
    }

}