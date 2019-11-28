package com.example.survey.withdrawal.wooppay.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.survey.withdrawal.exception.CannotAuthenticateException;
import com.example.survey.withdrawal.wooppay.RestOptionsProvider;
import com.example.survey.withdrawal.wooppay.mapper.AuthCredentialsMapper;
import com.example.survey.withdrawal.wooppay.model.AccessToken;
import com.example.survey.withdrawal.wooppay.model.AuthCredentials;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import static com.example.survey.withdrawal.wooppay.WooppayGlobals.*;

@Slf4j
@Service
public class AuthenticationService {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    RestOptionsProvider optionsProvider;

    @Autowired
    ObjectMapper objectMapper;

    public AccessToken authenticate() throws CannotAuthenticateException {
        AuthCredentials auth = new AuthCredentials(login, password);
        ResponseEntity<String> response = makeRequest(auth);
        return handleResponse(response);
    }

    private ResponseEntity<String> makeRequest(AuthCredentials auth) throws CannotAuthenticateException {
        try {
            return restTemplate.postForEntity(
                    com.example.survey.withdrawal.wooppay.WooppayGlobals.authUrl,
                    optionsProvider.getOptions(AuthCredentialsMapper.convertIntoJSON(auth), null),
                    String.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw new CannotAuthenticateException("Failed to execute auth request or while getting response");
        }
    }

    private AccessToken handleResponse(ResponseEntity<String> response) throws CannotAuthenticateException {
        if (response.getStatusCode() != HttpStatus.OK) {
            throw new CannotAuthenticateException("Authentication failed. Server response code: " + response.getStatusCodeValue());
        }
        try {
            return objectMapper.readValue(response.getBody(), AccessToken.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw new CannotAuthenticateException("Cannot build AccessToken object from server response below.\n" + response.getBody());
        }

    }
}
