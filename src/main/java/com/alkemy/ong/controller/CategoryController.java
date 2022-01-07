package com.alkemy.ong.controller;

import com.alkemy.ong.dto.CategoryDto;
import com.alkemy.ong.model.entity.Category;
import com.alkemy.ong.service.abstraction.ICategoryService;
import com.fasterxml.jackson.databind.introspect.TypeResolutionContext.Empty;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;

    @PostMapping
    public ResponseEntity<CategoryDto> save(@RequestBody CategoryDto categoryDto) {
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.save(categoryDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Empty> delete(@PathVariable long id) throws EntityNotFoundException {
        categoryService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<CategoryDto>> findAll() throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getOne(@PathVariable long id) throws EntityNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.getCategory(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDto> update(@PathVariable Long id, @RequestBody CategoryDto categoryDto)
            throws EntityNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.update(id, categoryDto));
    }

    @GetMapping("/page") //debe ser solo en el GET No /page VER PORQUE LIKE HASANYROLE NO FUNCIONA LA PETICION
    Page<Category> pagination(@RequestParam(required = false, defaultValue = "10") int pageSize, @RequestParam int page) {
        return categoryService.pagination(pageSize,page);
           }

}
