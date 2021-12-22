package com.alkemy.ong.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.validation.constraints.*;

@Setter
@Getter
@NoArgsConstructor
@Component
public class UserDto {

    @NotBlank (message = "El nombre no puede estar vacío ni ser nulo")
    @Pattern(regexp = "[a-zA-Z]+", message = "El nombre no puede contener números")
    public String firstName;

    @NotBlank (message = "El apellido no puede estar vacío ni ser nulo")
    @Pattern(regexp = "[a-zA-Z]+", message = "El apellido no puede contener números")
    public String lastName;

    @NotBlank (message = "El email no puede estar vacío")
    public String email;

    @NotBlank (message = "La contraseña no puede estar vacía")
    @Size(min = 6, max = 25, message = "La contraseña debe ser entre 6 y 25 caracteres")
    public String password;

    public String token;
}
