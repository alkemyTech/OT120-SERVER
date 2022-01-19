package com.alkemy.ong.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {

    public Long id;

    @NotEmpty (message = "El nombre no puede estar vacío ni ser nulo")
    private String name;

    @NotEmpty (message = "La descripción es mandatoria")
    public String description;

    public String image;

}
