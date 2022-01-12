package com.alkemy.ong.assembler;

import com.alkemy.ong.controller.CategoryController;
import com.alkemy.ong.dto.CategoryDto;
import com.alkemy.ong.model.entity.Category;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CategoryAssembler extends RepresentationModelAssemblerSupport<Category, CategoryDto> {

    public CategoryAssembler() {
        super(CategoryController.class, CategoryDto.class);
    }

    @Override
    public CategoryDto toModel(Category entity) {

        CategoryDto dto = instantiateModel(entity);

        dto.add(linkTo(
                methodOn(CategoryController.class)
                        .findById(entity.getId()))
                .withSelfRel());

        dto.setName(entity.getName());
        dto.setImage(entity.getImage());
        dto.setDescription(entity.getDescription());
        return dto;
    }

}
