package com.example.survey.limeApi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SurveyLanguageDetails {
    private String surveyls_title;
}
