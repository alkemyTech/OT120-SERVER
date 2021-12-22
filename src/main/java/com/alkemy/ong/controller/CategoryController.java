package com.alkemy.ong.controller;

import com.alkemy.ong.model.request.CategoryDto;
import com.alkemy.ong.model.entity.Category;
import com.alkemy.ong.dto.CategoryRequest;
import com.alkemy.ong.dto.CategoryResponse;
import com.alkemy.ong.service.CategoryServiceImpl;
import com.alkemy.ong.service.abstraction.ICategoryService;
import com.fasterxml.jackson.databind.introspect.TypeResolutionContext.Empty;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController {

    @Autowired
    private ICategoryService CategoryService;

    @Autowired
    private CategoryServiceImpl categoryService;

    @DeleteMapping(value = "/categories/{id}")
    public ResponseEntity<Empty> delete(@PathVariable long id) throws EntityNotFoundException {
        CategoryService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/categories")
    public ResponseEntity<List<CategoryDto>> findAll() throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.findAll());
    }

    @GetMapping("/categories/{id}")
    public ResponseEntity<Category> getOne(@PathVariable long id) throws EntityNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.getCategory(id));
    }

    @PutMapping("/categories/{id}")
    public ResponseEntity<CategoryResponse> update(@PathVariable long id, @RequestBody CategoryRequest categoryDto)
            throws EntityNotFoundException {
        CategoryResponse updatedCategory = categoryService.update(id, categoryDto);
        return ResponseEntity.ok().body(updatedCategory);
    }

}
