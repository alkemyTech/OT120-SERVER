package com.alkemy.ong.model.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@NoArgsConstructor
public class RegistrationResponse {

    public String username;
    public String firstName;
    public String lastName;
}
