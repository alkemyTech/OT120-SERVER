package com.alkemy.ong.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewsDto {

    @NotBlank(message = "El nombre no puede ser nulo")
    private String name;

    @NotBlank(message = "Debe contener información")
    private String content;

    @NotBlank(message = "Debe subir una imagen")
    private String image;

    @NotNull(message = "Elija una categoría")
    private Long category;
}
