package com.alkemy.ong.dto;

import java.io.Serializable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDto implements Serializable{

    public Long id;

    @NotEmpty (message = "El nombre no puede estar vacío ni ser nulo")
    private String name;

    @NotEmpty (message = "La descripción es mandatoria")
    public String description;

    public String image;

}
