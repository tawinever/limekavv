package com.example.survey.limeApi.service;

import com.example.survey.limeApi.exception.CannotAuthenticateException;
import com.example.survey.limeApi.model.SessionKey;
import com.example.survey.limeApi.service.base.LimeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class LimeAuthenticationService extends LimeService {

    public String getSessionKey() throws CannotAuthenticateException {
        //JSON PayLoad
        Map<String, Object> payLoad = new HashMap<>();
        payLoad.put("method", "get_session_key");
        payLoad.put("params", new String[]{login, password});
        payLoad.put("id", "1");

        //making http request
        ResponseEntity<String> ans =  restTemplate.postForEntity(
                rpcUrl,
                limeRestOptionsProvider.getOptions(payLoad),
                String.class);

        //parsing response JSON
        return handleResponse(ans);
    }

    private String handleResponse(ResponseEntity<String> response) throws CannotAuthenticateException {
        if (response.getStatusCode() != HttpStatus.OK) {
            throw new CannotAuthenticateException("Authentication failed. Server response code: " + response.getStatusCodeValue());
        }
        try {
            return objectMapper.readValue(response.getBody(), SessionKey.class).getResult();
        } catch (Exception e) {
            e.printStackTrace();
            throw new CannotAuthenticateException("Cannot get session key from server response below.\n" + response.getBody());
        }

    }
}
