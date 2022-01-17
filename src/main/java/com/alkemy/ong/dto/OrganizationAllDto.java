package com.alkemy.ong.dto;

import com.alkemy.ong.model.entity.Slide;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
public class OrganizationAllDto {

    @Size(min = 3, max = 255)
    private String name;

    @Size(min = 4, max = 255)
    private String image;

    @Size(min = 8, max = 11)
    private Integer phone;

    @NotBlank
    private String address;

    @NotBlank
    private String email;

    @NotBlank
    private String welcomeText;

    @NotBlank
    private String aboutUsText;

}
