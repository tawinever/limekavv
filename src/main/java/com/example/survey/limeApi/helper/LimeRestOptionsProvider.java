package com.example.survey.limeApi.helper;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
public class LimeRestOptionsProvider {

    public HttpEntity<Map<String, Object>> getOptions(Map<String, Object> JSON) {
        return new HttpEntity<>(JSON, getJSONHeaders());
    }

    private HttpHeaders getJSONHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        log.info(headers.toString());
        return headers;
    }

}