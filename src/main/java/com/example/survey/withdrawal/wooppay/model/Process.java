package com.example.survey.withdrawal.wooppay.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Process {
    private Operation operation;
}
