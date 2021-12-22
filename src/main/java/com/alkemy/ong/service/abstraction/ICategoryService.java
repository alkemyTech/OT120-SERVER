package com.alkemy.ong.service.abstraction;

import com.alkemy.ong.model.entity.Category;
import javax.persistence.EntityNotFoundException;
import com.alkemy.ong.dto.CategoryDto;

public interface ICategoryService {

    CategoryDto save(CategoryDto categoryDto);

    void delete(Long id) throws EntityNotFoundException;

    Category getCategory(Long id);
}
