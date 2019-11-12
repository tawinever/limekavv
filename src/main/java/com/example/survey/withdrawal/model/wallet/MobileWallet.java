package com.example.survey.withdrawal.model.wallet;

import lombok.Data;

@Data
public class MobileWallet {
    private Operator operator;
    private String phoneNumber;

    public MobileWallet() {
    }

    public MobileWallet(Operator operator, String phoneNumber) {
        this.operator = operator;
        this.phoneNumber = phoneNumber;
    }
}
