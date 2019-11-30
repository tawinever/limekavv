package com.example.survey.controller;

import com.example.survey.domain.Survey;
import com.example.survey.domain.User;
import com.example.survey.dto.PromoUserDto;
import com.example.survey.limeApi.LimeApi;
import com.example.survey.limeApi.exception.CannotAddParticipantException;
import com.example.survey.limeApi.exception.CannotAuthenticateException;
import com.example.survey.limeApi.exception.CannotParseException;
import com.example.survey.limeApi.exception.FailedHttpRequestException;
import com.example.survey.service.EmailService;
import com.example.survey.service.SurveyService;
import com.example.survey.service.UserService;
import com.sun.javaws.exceptions.InvalidArgumentException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.stream.Collectors;

@Slf4j
@Controller
public class PromoController {

    @Autowired
    LimeApi limeApi;

    @Autowired
    UserService userService;

    @Autowired
    SurveyService surveyService;

    @Autowired
    EmailService emailService;

    @Value("${lime.start.id}")
    protected String initSurveyId;

    @GetMapping("/promo")
    public String getPromo(Model model) {
        log.info("IN PromoController - getPromo");
        return "promo";
    }

    @PostMapping(value = "/promo", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String registerUserViaPromo(
            Model model,
            @Valid PromoUserDto usr,
            BindingResult bindingResult) {

        log.info("IN PromoController - registerUserViaProm");

        if (bindingResult.hasErrors()) {
            model.addAttribute("errors",
                    bindingResult.getFieldErrors().stream()
                            .map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList()));
            model.addAttribute("formData", usr);
            return "promo";
        }

        try {
            User user = userService.createNewUser(usr);
            surveyService.inviteUserToSurvey(user.getId(), Integer.parseInt(initSurveyId));
            String text = emailService.textBuilder(user.getEmail(), user.getPassword());
            emailService.sendSimpleMessage(user.getEmail(), "First letter", text);
        } catch (Exception | CannotAuthenticateException  | FailedHttpRequestException | CannotParseException e) {
            e.printStackTrace();
            model.addAttribute("errors", new String[]{"Something gone wrong...try later"});
            model.addAttribute("formData", usr);
            return "promo";
        }

        return "confirmed";
    }
}
