package com.example.survey.controller;

import com.example.survey.domain.MoneyTransfer;
import com.example.survey.domain.MoneyTransferEvent;
import com.example.survey.domain.MoneyTransferStatus;
import com.example.survey.domain.User;
import com.example.survey.dto.SurveyPaymentDto;
import com.example.survey.repository.MoneyTransferRepository;
import com.example.survey.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

@Slf4j
@RestController
@RequestMapping(value = "/api")
public class ApiController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    MoneyTransferRepository moneyTransferRepository;

    @PostMapping("/submitTransaction")
    //todo make dto validation
    //todo need refactor too many mess, different abstraction operations together
    //todo need to wrap 2 sql operations into one transaction
    public ResponseEntity HandleTransaction(@RequestBody SurveyPaymentDto surveyPaymentDto) {
        if (!Objects.equals(surveyPaymentDto.getToken(), "kot")) {
            log.info("Bad Request From Survey Server. Bad Token");
            return ResponseEntity.badRequest().build();
        }
        log.info("IN api - subTrans: {} ", surveyPaymentDto.getUserEmail());
        User user = userRepository.findByEmail(surveyPaymentDto.getUserEmail());
        if (user == null) {
            /*todo create new user in db not using null values, instead missing those columns in sql so that
               automatically will be acquired default values*/
            user = new User(
                    "New User",
                    surveyPaymentDto.getUserEmail(),
                    //todo make default values global
                    //todo read best practices how to store global values
                    "123123",
                    "+777712312300"
            );
            userRepository.save(user);
        }

        MoneyTransfer mt = new MoneyTransfer(
                MoneyTransferEvent.FINISHED_SURVEY,
                surveyPaymentDto.getSurveyName(),
                Math.toIntExact(user.getId()),
                MoneyTransferStatus.SUCCESS,
                surveyPaymentDto.getMoneyAmount(),
                new Timestamp((new Date()).getTime())
        );
        mt.setSurveyId(String.valueOf(surveyPaymentDto.getSurveyId()));

        moneyTransferRepository.save(mt);
        return ResponseEntity.noContent().build();
    }






}
