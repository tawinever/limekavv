package com.example.survey.limeApi;

import com.example.survey.limeApi.exception.CannotAddParticipantException;
import com.example.survey.limeApi.exception.CannotAuthenticateException;
import com.example.survey.limeApi.exception.CannotParseException;
import com.example.survey.limeApi.exception.FailedHttpRequestException;
import com.example.survey.limeApi.service.LimeAddParticipantService;
import com.example.survey.limeApi.service.LimeAuthenticationService;
import com.example.survey.limeApi.service.LimeGetSurveyLanguageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class LimeApiImpl implements LimeApi{
    @Autowired
    LimeAuthenticationService limeAuthenticationService;

    @Autowired
    LimeAddParticipantService limeAddParticipantService;

    @Autowired
    LimeGetSurveyLanguageService limeGetSurveyLanguageService;

    @Override
    public String addRespondentToInitSurvey(String email, String name) throws CannotAuthenticateException, CannotAddParticipantException {
        String surveyUrl = limeAddParticipantService.addParticipant(authenticate(), email, name);
        log.info("IN LimeApi - addRespondentToSurvey. Survey Url : {}", surveyUrl);
        return surveyUrl;
    }

    private String authenticate() throws CannotAuthenticateException {
        String sessionKey = limeAuthenticationService.getSessionKey();
        log.info("IN LimeApi - addRespondentToSurvey. Session Key : {}", sessionKey);
        return sessionKey;
    }

    @Override
    public String getSurveyTitle(int surveyId) throws CannotAuthenticateException, CannotParseException, FailedHttpRequestException {
        String surveyTitle = limeGetSurveyLanguageService.getSurveyTitle(authenticate(), surveyId);
        log.info("IN LimeApi - addRespondentToSurvey. Survey Url : {}", surveyTitle);
        return surveyTitle;
    }

}
