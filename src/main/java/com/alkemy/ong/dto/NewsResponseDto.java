package com.alkemy.ong.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewsResponseDto {

    @NotBlank (message = "El nombre no puede ser nulo")
    private String name;

    @NotBlank (message = "Debe contener información")
    private String content;

    @NotBlank (message = "Debe subir una imagen")
    private String image;

    @NotBlank(message = "Falta fecha")
    private Timestamp timestamp;

    @NotNull(message = "Elija una categoría")
    private Long category;
}
