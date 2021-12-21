package com.alkemy.ong.dto;

import java.sql.Timestamp;

import com.alkemy.ong.model.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsersResponseDto {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String photo;
    private Role role;
    private Timestamp timestamp;
}
