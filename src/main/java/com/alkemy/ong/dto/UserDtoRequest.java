package com.alkemy.ong.dto;

import java.sql.Timestamp;
import java.util.List;

import com.alkemy.ong.model.entity.Role;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.*;

@Setter
@Getter
@NoArgsConstructor
@Component
public class UserDtoRequest {

    @NotBlank (message = "El nombre no puede estar vacío ni ser nulo")
    @Pattern(regexp = "[a-zA-Z]+", message = "El nombre no puede contener números")
    private String firstName;

    @NotBlank (message = "El apellido no puede estar vacío ni ser nulo")
    @Pattern(regexp = "[a-zA-Z]+", message = "El apellido no puede contener números")
    private String lastName;

    @NotBlank (message = "El email no puede estar vacío")
    private String email;

    @NotBlank (message = "La contraseña no puede estar vacía")
    @Size(min = 6, max = 25, message = "La contraseña debe ser entre 6 y 25 caracteres")
    private String password;

    private Role rol;
    private String photo;
    private List<RoleDto> roles;
    private Timestamp timestamp;
    private String token;
}
