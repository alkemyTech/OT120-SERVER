package com.alkemy.ong.mapper;

import com.alkemy.ong.dto.PageDto;
import com.alkemy.ong.model.entity.Category;
import com.alkemy.ong.dto.CategoryDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public void categoryRefreshValue(Category entity, CategoryDto dto) {
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setImage(dto.getImage());
    }

    private List<CategoryDto> toCategoryDtoList(Page<Category> categoryPage) {

        List<CategoryDto> categoriesDtos = new ArrayList<>();

        if (categoryPage.hasContent()) {
            categoriesDtos = categoryPage.stream().map(category -> {
                return new CategoryDto(category.getName(), category.getDescription(), category.getImage(), category.getId());
            }).collect(Collectors.toList());
        }
        return categoriesDtos;
    }

    public PageDto<CategoryDto> toPageDto(Page<Category> categoryPage, Integer pageNumber, Integer totalPages) {

        PageDto<CategoryDto> pageDto = new PageDto<>();

        pageDto.setTotalPages(totalPages);

        if (categoryPage.hasNext()) {

            pageDto.setNextPage("/page?page=" + (pageNumber + 1));
        }

        if (categoryPage.hasPrevious()) {

            pageDto.setPreviousPage("/page?page=" + (pageNumber - 1));
        }
        pageDto.setList(toCategoryDtoList((categoryPage)));
        return pageDto;
    }
}