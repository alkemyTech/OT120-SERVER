package com.alkemy.ong.dto;

import java.sql.Timestamp;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String photo;
    private List<RoleDto> roles;
    private Timestamp timestamp;

}


