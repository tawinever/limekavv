package com.example.survey.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SurveyPaymentDto {
    private String userEmail;
    private String surveyName;
    private int surveyId;
    private int moneyAmount;
}
