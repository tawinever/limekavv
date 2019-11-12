package com.example.survey.controller;

import com.example.survey.dto.MobileWithdrawalDto;
import com.example.survey.service.MoneyTransferService;
import com.example.survey.withdrawal.exception.CannotAuthenticateException;
import com.example.survey.withdrawal.exception.NotEnoughMoneyException;
import com.example.survey.withdrawal.exception.RefusedPaymentException;
import com.example.survey.withdrawal.exception.ServiceBusyException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/transfer")
@Slf4j
public class MoneyTransferController {

    @Autowired
    MoneyTransferService moneyTransferService;


    @GetMapping("")
    public String main(@AuthenticationPrincipal UserDetails user, Model model) {
        model.addAttribute("balance", moneyTransferService.getBalance(user.getUsername()));
        return "transfer";
    }

    @PostMapping(value = "", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String processTransfer(
            @AuthenticationPrincipal UserDetails user,
            Model model,
            MobileWithdrawalDto withdrawal) {

        try {
            moneyTransferService.withdraw(user.getUsername(), withdrawal);
            model.addAttribute("note", "Money sent successfully!");
        } catch (NotEnoughMoneyException | ServiceBusyException | RefusedPaymentException | CannotAuthenticateException e) {
            model.addAttribute("flash", e.getMessage());
            e.printStackTrace();
        }

        model.addAttribute("balance", moneyTransferService.hetBalance(user.getUsername()));
        return "transfer";
    }

}
