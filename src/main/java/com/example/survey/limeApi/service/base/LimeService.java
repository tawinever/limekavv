package com.example.survey.limeApi.service.base;

import com.example.survey.limeApi.helper.LimeRestOptionsProvider;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

public class LimeService {
    @Autowired
    protected RestTemplate restTemplate;

    @Autowired
    protected LimeRestOptionsProvider limeRestOptionsProvider;

    @Autowired
    protected ObjectMapper objectMapper;

    @Value("${lime.rpc-url}")
    protected String rpcUrl;

    @Value("${lime.admin.login}")
    protected String login;

    @Value("${lime.admin.password}")
    protected String password;

    @Value("${lime.start.id}")
    protected String initSurveyId;

    @Value("${lime.base-url}")
    protected String baseUrl;

}
