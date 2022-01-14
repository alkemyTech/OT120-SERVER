package com.alkemy.ong.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TestimonialResponseDto {

    @NotBlank(message = "Testimonial must have a name")
    public String name;

    @NotBlank(message = "Content can't be empty")
    public String content;

    public String image;

}
