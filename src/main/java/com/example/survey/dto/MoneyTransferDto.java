package com.example.survey.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.sql.Timestamp;


@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MoneyTransferDto {
    private int id;
    private String event;
    private String target;
    private Timestamp createDt;
    private int moneyAmount;

    public MoneyTransferDto() {
    }

    public MoneyTransferDto(int id, String event, String target, Timestamp createDt, int moneyAmount) {
        this.id = id;
        this.event = event;
        this.target = target;
        this.createDt = createDt;
        this.moneyAmount = moneyAmount;
    }
}
