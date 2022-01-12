package com.alkemy.ong.controller;

import com.alkemy.ong.assembler.CategoryAssembler;
import com.alkemy.ong.dto.CategoryDto;
import com.alkemy.ong.model.entity.Category;
import com.alkemy.ong.service.abstraction.ICategoryService;
import com.fasterxml.jackson.databind.introspect.TypeResolutionContext.Empty;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletResponse;

import com.sun.net.httpserver.HttpsServer;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.hateoas.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private CategoryAssembler categoryAssembler;

    @Autowired
    private PagedResourcesAssembler<Category> pagedResourcesAssembler;

    @PostMapping
    public ResponseEntity<CategoryDto> save(@RequestBody CategoryDto categoryDto) {
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.save(categoryDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Empty> delete(@PathVariable long id) throws EntityNotFoundException {
        categoryService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

//    @GetMapping
//    public ResponseEntity<List<CategoryDto>> findAll() throws Exception {
//        return ResponseEntity.status(HttpStatus.OK).body(categoryService.findAll());
//    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getOne(@PathVariable long id) throws EntityNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.getCategory(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDto> update(@PathVariable Long id, @RequestBody CategoryDto categoryDto)
            throws EntityNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.update(id, categoryDto));
    }

//    @GetMapping("/page")
//    //debe ser solo en el GET No /page VER PORQUE LIKE HASANYROLE NO FUNCIONA LA PETICION
//    //VER DE BORRAR LO QUE ESTE DE MAS Y LA INYECCION DE CATEGORIAS, CREO QN EN EL MAIN ERA
//    public ResponseEntity<Page<Category>> pagination(@RequestParam(required = false, defaultValue = "10") int pageSize, @RequestParam int page) {
//        return ResponseEntity.status(HttpStatus.OK).body(categoryService.pagination(pageSize, page));
//    }


    @GetMapping(params = "page")
    public ResponseEntity<PagedModel<CategoryDto>> findAll(Pageable pageable, @RequestParam("page") int page) throws NotFoundException {
        Page<Category> entity = categoryService.readAll(pageable, page);
        PagedModel<CategoryDto> dto = pagedResourcesAssembler.toModel(entity, categoryAssembler);

        return new ResponseEntity<PagedModel<CategoryDto>>(dto, HttpStatus.OK);

    }


    public Optional<Category> findById(Long id) {
        return categoryService.findByid(id);
    }
}
