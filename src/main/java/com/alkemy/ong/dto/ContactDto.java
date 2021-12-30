package com.alkemy.ong.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ContactDto {

    private String name;
    private Long phone;
    private String email;
    private String message;
}
