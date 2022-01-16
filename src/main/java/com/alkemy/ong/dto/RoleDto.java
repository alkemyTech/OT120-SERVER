package com.alkemy.ong.model.dto;

import java.sql.Timestamp;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleDto {

    private Long id;
    private String name;
    private String description;
    private Timestamp timestamp;
    private List<UserDtoRequest> users;
}


