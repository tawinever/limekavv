package com.example.survey.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender emailSender;

    @Value("${soft.login-url}")
    private String loginUrl;

    public void sendSimpleMessage(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }

    public String textBuilder(String login, String password) {
        return "Hello! Go to this website: " + loginUrl + "\n" +
                "Your login: " + login + "\n" +
                "Your password" + password;
    }
}
