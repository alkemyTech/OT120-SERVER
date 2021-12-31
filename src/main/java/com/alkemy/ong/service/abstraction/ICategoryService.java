package com.alkemy.ong.service.abstraction;

import com.alkemy.ong.dto.CategoryRequest;
import com.alkemy.ong.dto.CategoryResponse;
import com.alkemy.ong.model.entity.Category;
import javax.persistence.EntityNotFoundException;
import com.alkemy.ong.dto.CategoryDto;

public interface ICategoryService {

  void delete(Long id) throws EntityNotFoundException;

  CategoryResponse update(long id, CategoryRequest updatedCategory) throws EntityNotFoundException;

  CategoryDto save(CategoryDto categoryDto);

  Category getCategory(Long id);
}
