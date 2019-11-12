package com.example.survey.withdrawal.wooppay.mapper;

import com.example.survey.withdrawal.wooppay.model.AuthCredentials;

import java.util.HashMap;
import java.util.Map;

public class AuthCredentialsMapper {
    public static Map<String, Object> convertIntoJSON(AuthCredentials authCredentials) {
        Map<String, Object> map = new HashMap<>();
        map.put("login", authCredentials.getLogin());
        map.put("password", authCredentials.getPassword());
        return map;
    }
}
