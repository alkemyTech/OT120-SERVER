package com.alkemy.ong.dto;

import com.alkemy.ong.common.ValidationMessages;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class MemberDto {

    @NotBlank(message = ValidationMessages.REQUEST_PARAM_EMPTY_ERROR_MESSAGE)
    @Pattern(regexp = ValidationMessages.REGEX_VALIDATION_STRING, message = ValidationMessages.REGEX_VALIDATION_STRING_MESSAGE)
    @Size(max = 250, message = ValidationMessages.REQUEST_PARAM_MAX_ERROR_MESSAGE)
    private String nombre;

    private String image;
    private String description;
    private String timestamps;

}
