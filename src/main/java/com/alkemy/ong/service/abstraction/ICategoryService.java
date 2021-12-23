package com.alkemy.ong.service.abstraction;

import com.alkemy.ong.dto.CategoryRequest;
import com.alkemy.ong.dto.CategoryResponse;
import com.alkemy.ong.model.entity.Category;
import javax.persistence.EntityNotFoundException;
import com.alkemy.ong.dto.CategoryDto;

public interface ICategoryService {

<<<<<<< HEAD
  void delete(Long id) throws EntityNotFoundException;

  CategoryResponse update(long id, CategoryRequest updatedCategory) throws EntityNotFoundException;

  Category getCategory(Long id);
=======
    CategoryDto save(CategoryDto categoryDto);

    void delete(Long id) throws EntityNotFoundException;

    Category getCategory(Long id);
>>>>>>> 3ab4f1c71d40fe0de18c08e1370f406e96fc52a2
}
