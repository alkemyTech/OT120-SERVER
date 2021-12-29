package com.alkemy.ong.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public class ContactDto {

    @NotEmpty
    @Size(min= 3, max = 255)
    public String name;

    @Positive
    @NotEmpty
    @Size(min= 3, max = 255)
    public Long phone;

    @NotEmpty
    @Size(min= 3, max = 255)
    @Email
    public String email;
}
