package com.example.survey.controller;

import com.example.survey.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/stats")
public class StatisticsController {

    @Autowired
    StatisticsService statisticsService;

    @GetMapping("")
    public String main(@AuthenticationPrincipal UserDetails user, Model model) {
        model.addAttribute("transfers", statisticsService.getAllMoneyTransferData());
        model.addAttribute("totalEarnedSum", statisticsService.getTotalEarnedSum());
        model.addAttribute("totalWithdrawnSum", statisticsService.getTotalWithdrawnSum());

        return "stats";
    }
}
