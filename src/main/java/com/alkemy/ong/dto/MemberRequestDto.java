package com.alkemy.ong.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
public class MemberRequestDto {

    @NotBlank(message = "El atributo %s no puede estar vacio")
    @Pattern(regexp = "^([a-zA-Z]+)$", message = "Este campo no debe contener números")
    @Size(max = 250, message = "El atributo %s no debe tener mas de {max} caracteres")
    private String name;
    private Long id;
    private String image;
    private String description;
    private Timestamp timestamps;

}
