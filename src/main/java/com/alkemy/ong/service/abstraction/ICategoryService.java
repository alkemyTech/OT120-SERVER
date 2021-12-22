package com.alkemy.ong.service.abstraction;

import com.alkemy.ong.dto.CategoryRequest;
import com.alkemy.ong.dto.CategoryResponse;
import com.alkemy.ong.model.entity.Category;

import javax.persistence.EntityNotFoundException;

public interface ICategoryService {

  void delete(Long id) throws EntityNotFoundException;

  CategoryResponse update(long id, CategoryRequest updatedCategory) throws EntityNotFoundException;

  Category getCategory(Long id);
}
