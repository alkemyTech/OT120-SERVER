package com.alkemy.ong.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContactDto implements Serializable {

    @NotBlank(message = "El nombre no puede estar vacío ni ser nulo")
    private String name;

    private Long phone;

    @NotBlank (message = "El email no puede estar vacío ni ser nulo")
    private String email;

    private String message;

    private boolean deletedAt = Boolean.FALSE;
}
