package com.example.survey.withdrawal.exception;

public class NotEnoughMoneyException extends Throwable {
    public NotEnoughMoneyException(String m) {
        super(m);
    }
}