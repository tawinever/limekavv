package com.example.survey.limeApi;

import com.example.survey.limeApi.exception.CannotAddParticipantException;
import com.example.survey.limeApi.exception.CannotAuthenticateException;
import com.example.survey.limeApi.exception.CannotParseException;
import com.example.survey.limeApi.exception.FailedHttpRequestException;

public interface LimeApi {
    String addRespondentToSurvey(String email, String name, int surveyId) throws CannotAuthenticateException, CannotAddParticipantException;

    String getSurveyTitle(int surveyId) throws CannotAuthenticateException, CannotParseException, FailedHttpRequestException;


}
