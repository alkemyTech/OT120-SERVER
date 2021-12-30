package com.alkemy.ong.dto;

import lombok.AllArgsConstructor;
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


}
