package com.example.survey.limeApi.service;

import com.example.survey.limeApi.exception.CannotAddParticipantException;
import com.example.survey.limeApi.exception.CannotAuthenticateException;
import com.example.survey.limeApi.model.ResultToken;
import com.example.survey.limeApi.model.SessionKey;
import com.example.survey.limeApi.model.Token;
import com.example.survey.limeApi.service.base.LimeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LimeAddParticipantService extends LimeService {

    public String addParticipant(String sessionKey, String email, String firstName) throws CannotAddParticipantException {
        //JSON PayLoad
        Map<String, String> participant = new HashMap<>();
        participant.put("email", email);
        participant.put("firstName", firstName);
        List<Map<String, String>> partAr = new ArrayList<>();
        partAr.add(participant);

        Map<String, Object> payLoad = new HashMap<>();
        payLoad.put("method", "add_participants");
        payLoad.put("params", new Object[]{sessionKey, initSurveyId, partAr});
        payLoad.put("id", "1");

        //making http request
        ResponseEntity<String> ans =  restTemplate.postForEntity(
                rpcUrl,
                limeRestOptionsProvider.getOptions(payLoad),
                String.class);

        //parsing response JSON
        List<Token> res = handleResponse(ans);

        return baseUrl + initSurveyId + "?token=" + res.get(0).getToken();
    }

    private List<Token> handleResponse(ResponseEntity<String> response) throws CannotAddParticipantException {
        if (response.getStatusCode() != HttpStatus.OK) {
            throw new CannotAddParticipantException("Add Participants failed. Server response code: " + response.getStatusCodeValue());
        }
        try {
            return objectMapper.readValue(response.getBody(), ResultToken.class).getResult();
        } catch (Exception e) {
            e.printStackTrace();
            throw new CannotAddParticipantException("Cannot get participant token from server response below.\n" + response.getBody());
        }

    }

}
