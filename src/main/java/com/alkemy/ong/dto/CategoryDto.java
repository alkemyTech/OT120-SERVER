
package com.alkemy.ong.dto;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto implements Serializable{

    @NotEmpty (message = "El nombre no puede estar vacío ni ser nulo")
    private String name;


}
