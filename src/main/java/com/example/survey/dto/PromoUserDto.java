package com.example.survey.dto;

import com.example.survey.dto.validator.UniqueEmail;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
public class PromoUserDto {

    @NotNull
    @Email
    @UniqueEmail
    private String email;

    @NotNull
    private String name;
}
