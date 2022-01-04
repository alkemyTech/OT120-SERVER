package com.alkemy.ong.service.abstraction;

import com.alkemy.ong.model.entity.Category;
import javax.persistence.EntityNotFoundException;
import com.alkemy.ong.dto.CategoryDto;
import java.util.List;

public interface ICategoryService {

  void delete(Long id) throws EntityNotFoundException;

  CategoryDto update(Long id, CategoryDto categoryDto) throws EntityNotFoundException;

  CategoryDto save(CategoryDto categoryDto);

  Category getCategory(Long id);

  List<CategoryDto> findAll();
}
