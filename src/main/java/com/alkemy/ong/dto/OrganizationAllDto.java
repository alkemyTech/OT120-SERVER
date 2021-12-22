package com.alkemy.ong.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Getter
@Setter
public class OrganizationAllDto {

    @Size(min = 3, max = 255)
    private String name;

    @Size(min = 4, max = 255)
    private String image;

    @Positive
    @Size(min = 8, max = 11)
    private Integer phone;

    private String address;

    private String email;

    private String welcomeText;

    private String aboutUsText;

}
