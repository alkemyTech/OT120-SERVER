package com.alkemy.ong.dto;

import lombok.AllArgsConstructor;
<<<<<<< HEAD
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
=======
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
public class ContactDto {

    @NotBlank(message = "Email is mandatory.")
    private String email;

    @NotBlank(message = "name is mandatory.")
    private String name;

    private long phone;


>>>>>>> 2eb43957eb46d80cd3aeae3b4cf9eaaad10d4817
}
