package com.example.survey.dto;

import com.example.survey.dto.validator.AdvancedIin;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.validation.constraints.*;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDetailsDto {

    @AdvancedIin
    private String iin;

    @NotNull
    @NotBlank
    private String surname;

    @NotNull
    @NotBlank
    private String middlename;

    @NotNull
    @Min(0)
    @Max(1)
    private int gender;

}
