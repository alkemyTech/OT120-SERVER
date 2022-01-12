package com.alkemy.ong.dto;

import java.io.Serializable;

import com.alkemy.ong.model.entity.Category;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDto extends RepresentationModel<Category> implements Serializable{

    @NotEmpty (message = "El nombre no puede estar vacío ni ser nulo")
    private String name;

    @NotEmpty (message = "La descripción es mandatoria")
    public String description;

    public String image;

    public Long id;
}
