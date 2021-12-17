package com.alkemy.ong.controller;

import com.alkemy.ong.service.CategoryServiceImpl;
import com.alkemy.ong.service.abstraction.IDeleteCategoryService;
import com.fasterxml.jackson.databind.introspect.TypeResolutionContext.Empty;
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
  private CategoryServiceImpl categoryService;

  @DeleteMapping(value = "/categories/{id}")
  public ResponseEntity<Empty> delete(@PathVariable long id) throws EntityNotFoundException {
    deleteCategoryService.delete(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @GetMapping("/categories/{id}")
  public ResponseEntity<?> getOne(@PathVariable long id){
    try{
      return ResponseEntity.status(HttpStatus.OK).body(categoryService.getCategory(id));
    }catch (Exception e){
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Category not found.\"}");
    }
  }

}
