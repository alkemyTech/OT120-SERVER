package com.alkemy.ong.controller;

import com.alkemy.ong.model.request.CategoryDto;
import com.alkemy.ong.service.CategoryDtoService;
import com.alkemy.ong.service.abstraction.IDeleteCategoryService;
import com.fasterxml.jackson.databind.introspect.TypeResolutionContext.Empty;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoryController {

    @Autowired
    private IDeleteCategoryService deleteCategoryService;
    
    @Autowired
    private CategoryDtoService categoryService;

    @DeleteMapping(value = "/categories/{id}")
    public ResponseEntity<Empty> delete(@PathVariable long id) throws EntityNotFoundException {
        deleteCategoryService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value ="/categories")
    public ResponseEntity<List<CategoryDto>> findAll() throws Exception{
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.findAll());
    }    
}
