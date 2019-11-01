package com.example.survey.controller;

import com.example.survey.domain.MoneyTransfer;
import com.example.survey.domain.Role;
import com.example.survey.domain.User;
import com.example.survey.dto.MoneyTransferDto;
import com.example.survey.dto.ProfileDto;
import com.example.survey.repository.UserRepository;
import org.springframework.http.MediaType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class MainController {
    private final UserRepository userRepository;

    public MainController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @GetMapping("/")
    //todo extend UserDetails class so that we can store all user information in authorized object
    public String dashboard(@AuthenticationPrincipal UserDetails user, Model model) {
        //todo figure out is it allowed to write private methods in controller

        User domainUser = userRepository.findByEmail(user.getUsername());
        List<MoneyTransferDto> bills = domainUser.getBills().stream()
                .map(this::convertIntoDto)
                .collect(Collectors.toList());
        int balance = bills.stream().mapToInt(MoneyTransferDto::getMoneyAmount).sum();

        model.addAttribute("balance", balance);
        model.addAttribute("bills", bills);
        model.addAttribute("email", user.getUsername());
        for (GrantedAuthority auth : user.getAuthorities()) {
            model.addAttribute("role", Role.values()[Integer.parseInt(auth.getAuthority())]);
        }



        return "dashboard";
    }

    private MoneyTransferDto convertIntoDto(MoneyTransfer mt) {
        return  new MoneyTransferDto(
                mt.getId(),
                mt.getEvent().toString(),
                mt.getTarget(),
                mt.getCreateDt(),
                mt.getMoneyAmount()
        );
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
}
