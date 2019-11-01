package com.example.survey.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.sql.Timestamp;


@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProfileDto {
    private String name;
    private String phone;

    public ProfileDto() {
    }

    public ProfileDto(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }
}
