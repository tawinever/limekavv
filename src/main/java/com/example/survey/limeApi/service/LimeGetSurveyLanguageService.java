package com.example.survey.limeApi.service;

import com.example.survey.limeApi.exception.CannotAddParticipantException;
import com.example.survey.limeApi.exception.CannotParseException;
import com.example.survey.limeApi.exception.FailedHttpRequestException;
import com.example.survey.limeApi.model.ResultToken;
import com.example.survey.limeApi.model.SurveyLanguageDetailsWrapper;
import com.example.survey.limeApi.model.Token;
import com.example.survey.limeApi.service.base.LimeService;
import com.fasterxml.jackson.core.JsonParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LimeGetSurveyLanguageService extends LimeService {

    public String getSurveyTitle(String sessionKey, int surveyId) throws CannotParseException, FailedHttpRequestException {
        //JSON PayLoad

        Map<String, Object> payLoad = new HashMap<>();
        payLoad.put("method", "get_language_properties");
        payLoad.put("params", new Object[]{sessionKey, surveyId, null, null});
        payLoad.put("id", "1");

        //making http request
        ResponseEntity<String> ans =  restTemplate.postForEntity(
                rpcUrl,
                limeRestOptionsProvider.getOptions(payLoad),
                String.class);

        //parsing response JSON

        return handleResponse(ans);
    }

    private String handleResponse(ResponseEntity<String> response) throws FailedHttpRequestException, CannotParseException {
        if (response.getStatusCode() != HttpStatus.OK) {
            throw new FailedHttpRequestException("Add Participants failed. Server response code: " + response.getStatusCodeValue());
        }
        try {
            return objectMapper.readValue(response.getBody(), SurveyLanguageDetailsWrapper.class).getResult().getSurveyls_title();
        } catch (Exception e) {
            e.printStackTrace();
            throw new CannotParseException("Cannot get participant token from server response below.\n" + response.getBody());
        }

    }

}
