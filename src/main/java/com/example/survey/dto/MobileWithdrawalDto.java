package com.example.survey.dto;

import com.example.survey.withdrawal.model.wallet.Operator;
import lombok.Data;

@Data
public class MobileWithdrawalDto {
    private Operator operator;
    private String phoneNumber;
    private Double amount;

    public String toString() {
        return "Mobile withdrawal data : {\n" +
                "operator: " + operator + "\n" +
                "phoneNumber: " + phoneNumber + "\n" +
                "amount: " + amount + "\n}\n";
    }
    public String toInlineString() {
        return operator + ", number: " + phoneNumber;
    }

    public String formattedPhoneNumber() {
        String cur = phoneNumber;
        cur = cur.replaceAll("[^\\d]","").substring(1);
        return cur;
    }

}
