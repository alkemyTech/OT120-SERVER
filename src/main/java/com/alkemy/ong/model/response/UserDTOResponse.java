package com.alkemy.ong.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTOResponse {

    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private String photo;
    private String jwt;

}
