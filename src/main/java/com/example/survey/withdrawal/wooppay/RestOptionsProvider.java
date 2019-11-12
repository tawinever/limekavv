package com.example.survey.withdrawal.wooppay;

import com.example.survey.withdrawal.wooppay.model.AccessToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
public class RestOptionsProvider {

    public HttpEntity<Map<String, Object>> getOptions(Map<String, Object> JSON, AccessToken token) {
        return new HttpEntity<>(JSON, getJSONHeaders(token));
    }

    private HttpHeaders getJSONHeaders(AccessToken accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        if (accessToken != null)
            headers.set("Authorization", accessToken.getToken());
        log.info(headers.toString());
        return headers;
    }

}
