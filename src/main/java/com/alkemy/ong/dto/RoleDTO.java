package com.alkemy.ong.dto;

import java.sql.Timestamp;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleDTO {

    private Long id;
    private String name;
    private String description;
    private Timestamp timestamp;
    private List<UserDTO> users;
}


