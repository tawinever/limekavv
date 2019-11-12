package com.example.survey.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class StatisticMoneyTransferDto {
    private String name;
    private String email;
    private long date;
    private String event;
    private Double sum;
    private String status;

    public StatisticMoneyTransferDto() {
    }

    public StatisticMoneyTransferDto(String name, String email, long date, String event, Double sum, String status) {
        this.name = name;
        this.email = email;
        this.date = date;
        this.event = event;
        this.sum = sum;
        this.status = status;
    }
}
