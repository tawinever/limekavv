package com.example.survey.controller;

import com.example.survey.domain.Gender;
import com.example.survey.domain.User;
import com.example.survey.dto.UserDetailsDto;
import com.example.survey.dto.MoneyTransferDto;
import com.example.survey.dto.ProfileDto;
import com.example.survey.service.MoneyTransferService;
import com.example.survey.withdrawal.WithdrawalService;
import com.example.survey.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.MediaType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
public class MainController {
    private final UserRepository userRepository;
    private final WithdrawalService withdrawalService;
    private final MoneyTransferService moneyTransferService;

    public MainController(UserRepository userRepository, WithdrawalService withdrawalService, MoneyTransferService moneyTransferService) {
        this.userRepository = userRepository;
        this.withdrawalService = withdrawalService;
        this.moneyTransferService = moneyTransferService;
    }


    @GetMapping("/")
    //todo extend UserDetails class so that we can store all user information in authorized object
    public String dashboard(@AuthenticationPrincipal UserDetails user, Model model) {
        //todo figure out is it allowed to write private methods in controller

        List<MoneyTransferDto> bills = moneyTransferService.getMoneyTransfer(user.getUsername());
        double balance = moneyTransferService.getBalance(user.getUsername());

        model.addAttribute("balance", balance);
        Collections.reverse(bills);
        model.addAttribute("bills", bills);
        model.addAttribute("email", user.getUsername());
        for (GrantedAuthority auth : user.getAuthorities()) {
            log.info(auth.toString());
            log.info(auth.getAuthority());
        }
        return "dashboard";
    }



    @GetMapping("/profile")
    public String profile(@AuthenticationPrincipal UserDetails user, Model model) {
        User curUser = userRepository.findByEmail(user.getUsername());
        model.addAttribute("usr", curUser);
        return "profile";
    }

    @PostMapping(value = "/profile", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String setProfile(
            @AuthenticationPrincipal UserDetails user,
            Model model,
            ProfileDto profileDto) {
        User curUser = userRepository.findByEmail(user.getUsername());
        curUser.setName(profileDto.getName());
        curUser.setPhone(profileDto.getPhone());
        userRepository.save(curUser);
        model.addAttribute("usr", curUser);
        return "profile";
    }

    @PostMapping(value = "/updateIin", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String setIin(
            @AuthenticationPrincipal UserDetails user,
            Model model,
            @Valid UserDetailsDto userDetailsDto,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {

        log.info("IN MainController - setIin");

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("errors",
                    bindingResult.getFieldErrors().stream()
                            .map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList()));
            return "redirect:/transfer";
        }

        User curUser = userRepository.findByEmail(user.getUsername());

        if (curUser.getIin() != null) {
            return "redirect:/profile";
        }

        curUser.setIin(userDetailsDto.getIin());
        curUser.setGender(Gender.values()[userDetailsDto.getGender()]);
        curUser.setSurname(userDetailsDto.getSurname());
        curUser.setMiddlename(userDetailsDto.getMiddlename());
        userRepository.save(curUser);

        return "redirect:/profile";
    }


}
