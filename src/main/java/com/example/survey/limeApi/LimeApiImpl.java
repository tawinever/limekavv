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

    private String sessionKey;

    @Override
    public String addRespondentToSurvey(String email, String name, int surveyId) throws CannotAuthenticateException, CannotAddParticipantException {
        authenticate();

        String surveyUrl = limeAddParticipantService.addRespondent(sessionKey, email, name, surveyId);

        log.info("IN LimeApi - addRespondentToSurvey. Survey Url : {}", surveyUrl);

        logout();
        return surveyUrl;
    }

    @Override
    public String getSurveyTitle(int surveyId) throws CannotAuthenticateException, CannotParseException, FailedHttpRequestException {
        authenticate();

        String surveyTitle = limeGetSurveyLanguageService.getSurveyTitle(sessionKey, surveyId);

        log.info("IN LimeApi - getSurveyTitle. Survey title : {}", surveyTitle);

        logout();
        return surveyTitle;
    }

    private void authenticate() throws CannotAuthenticateException {
        this.sessionKey = limeAuthenticationService.getSessionKey();
        log.info("IN LimeApi - authenticate - getting Session Key : {}", this.sessionKey);
    }

    private void logout() {
        limeAuthenticationService.logout(this.sessionKey);
        log.info("IN LimeApi - logout - releasing Session Key : {}", this.sessionKey);
    }

}
