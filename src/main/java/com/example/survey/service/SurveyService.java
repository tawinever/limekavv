package com.example.survey.service;

import com.example.survey.domain.Survey;
import com.example.survey.domain.Ticket;
import com.example.survey.domain.TicketStatus;
import com.example.survey.domain.User;
import com.example.survey.limeApi.LimeApi;
import com.example.survey.limeApi.exception.CannotAddParticipantException;
import com.example.survey.limeApi.exception.CannotAuthenticateException;
import com.example.survey.limeApi.exception.CannotParseException;
import com.example.survey.limeApi.exception.FailedHttpRequestException;
import com.example.survey.repository.SurveyRepository;
import com.example.survey.repository.TicketRepository;
import com.example.survey.repository.UserRepository;
import com.sun.javaws.exceptions.InvalidArgumentException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SurveyService {

    @Autowired
    LimeApi limeApi;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    SurveyRepository surveyRepository;

    @Value("${lime.start.id}")
    protected String initSurveyId;

    public void inviteUserToSurvey(Long userId) throws InvalidArgumentException, CannotAuthenticateException, CannotAddParticipantException, CannotParseException, FailedHttpRequestException {
        User user = userRepository.findById((long) userId).orElse(null);
        if (user == null )  throw new InvalidArgumentException(new String[]{"There is no user with such ID"});

        String url = "default";

        Ticket ticket = new Ticket();
        ticket.setSurveyId(getSurveyId(Integer.parseInt(initSurveyId)));
        ticket.setUserId(userId);
        ticket.setStatus(TicketStatus.NEW);
        ticket.setUrl(url);

        try {
            ticketRepository.save(ticket);
        } catch (Exception e) {
            log.error("Cannot create Ticket. Ticket : {}", ticket);
            throw e;
        }

        try {
            url = limeApi.addRespondentToInitSurvey(user.getEmail(), user.getName());
        } catch (Exception e) {
            ticketRepository.delete(ticket);
            throw e;
        }

        ticket.setUrl(url);
        ticketRepository.save(ticket);
    }

    private Integer getSurveyId(int limeId) throws FailedHttpRequestException, CannotParseException, CannotAuthenticateException {
        Survey survey = surveyRepository.findByLimeId(limeId);
        if (survey == null) {
            survey = new Survey();
            survey.setLimeId(limeId);
            survey.setTitle(limeApi.getSurveyTitle(limeId));
            surveyRepository.save(survey);
        }
        return survey.getId().intValue();
    }
}
