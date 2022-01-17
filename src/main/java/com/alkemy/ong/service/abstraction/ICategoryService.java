package com.alkemy.ong.service.abstraction;

import com.alkemy.ong.dto.PageDto;
import com.alkemy.ong.model.entity.Category;
import javax.persistence.EntityNotFoundException;
import com.alkemy.ong.dto.CategoryDto;
import javassist.NotFoundException;

import java.util.List;

public interface ICategoryService {


    void delete(Long id) throws EntityNotFoundException;

     CategoryDto update(Long id, CategoryDto categoryDto) throws EntityNotFoundException;

    CategoryDto save(CategoryDto categoryDto);

    Category getCategory(Long id);

    List<CategoryDto> findAll();

    PageDto<CategoryDto> getPage(Integer page, Integer sizePage, String sortBy) throws NotFoundException;
}
