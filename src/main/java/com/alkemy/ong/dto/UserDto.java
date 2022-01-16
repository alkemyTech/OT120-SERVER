package com.alkemy.ong.model.dto;

import com.alkemy.ong.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private String firstName;

    private String lastName;

    private String email;

    private String role;

    private Date creationDate;

    public static UserDto userToDto(User user) {
        return UserDto.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .role(user.getRoles().toString())
                .build();
    }

}
