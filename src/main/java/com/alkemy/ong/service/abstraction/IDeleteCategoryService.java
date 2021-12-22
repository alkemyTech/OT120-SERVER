package com.alkemy.ong.service.abstraction;

import com.alkemy.ong.dto.CategoryRequest;
import com.alkemy.ong.dto.CategoryResponse;

import javax.persistence.EntityNotFoundException;

public interface IDeleteCategoryService {

  void delete(Long id) throws EntityNotFoundException;

  CategoryResponse update(long id, CategoryRequest updatedCategory) throws EntityNotFoundException;

}
