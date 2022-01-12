package com.alkemy.ong.service.abstraction;

import com.alkemy.ong.model.entity.Category;

import javax.persistence.EntityNotFoundException;

import com.alkemy.ong.dto.CategoryDto;
import javassist.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ICategoryService {

   
    void delete(Long id) throws EntityNotFoundException;

    CategoryDto update(Long id, CategoryDto categoryDto) throws EntityNotFoundException;

    CategoryDto save(CategoryDto categoryDto);

    Category getCategory(Long id);

    List<CategoryDto> findAll() throws NotFoundException;

    Page<Category> readAll(Pageable pageable, int page) throws NotFoundException;

    Page<Category> pagination(int pageSize, int pageNumber);

    Optional<Category> findByid(Long id);
}
