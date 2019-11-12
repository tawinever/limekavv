package com.example.survey.withdrawal.wooppay.model;

import lombok.Data;

@Data
public class AuthCredentials {
    private String login;
    private String password;

    public AuthCredentials() {
    }

    public AuthCredentials(String login, String password) {
        this.login = login;
        this.password = password;
    }

}
