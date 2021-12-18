package com.alkemy.ong.model.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Setter
@Getter
@NoArgsConstructor
public class RegistrationRequest {

    @NotNull (message = "El nombre no puede ser nulo")
    @NotBlank
    @NotEmpty (message = "El nombre no puede estar vacío")
    public String firstName;

    @NotNull (message = "El apellido no puede ser nulo")
    @NotBlank
    @NotEmpty (message = "El apellido no puede estar vacío")
    public String lastName;

    @NotNull (message = "El email no puede ser nulo")
    @NotBlank
    @NotEmpty (message = "El email no puede estar vacío")
    public String email;

    @NotNull
    @NotBlank
    @NotEmpty (message = "La contraseña no puede estar vacía")
    @Size(min = 6, max = 25, message = "La contraseña debe ser entre 6 y 25 caracteres")
    public String password;
}
