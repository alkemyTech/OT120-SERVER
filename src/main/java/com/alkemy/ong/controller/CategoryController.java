package com.alkemy.ong.controller;

import com.alkemy.ong.dto.CategoryDto;
import com.alkemy.ong.model.entity.Category;
import com.alkemy.ong.service.CategoryServiceImpl;
import com.alkemy.ong.service.abstraction.ICategoryService;
import com.alkemy.ong.service.abstraction.IDeleteCategoryService;
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
    private IDeleteCategoryService deleteCategoryService;

    @Autowired
    private CategoryServiceImpl categoryService;

    @Autowired
    private ICategoryService iCategoryService;

    @PostMapping("/categories")
    public ResponseEntity<CategoryDto> save(@RequestBody CategoryDto categoryDto) {
        CategoryDto newCategory = iCategoryService.save(categoryDto);
        return new ResponseEntity<>(newCategory, HttpStatus.OK);
    }

    @DeleteMapping(value = "/categories/{id}")
    public ResponseEntity<Empty> delete(@PathVariable long id) throws EntityNotFoundException {
        deleteCategoryService.delete(id);
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

}
