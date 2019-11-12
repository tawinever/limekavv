package com.example.survey.withdrawal.exception;

public class RefusedPaymentException extends Throwable {
    public RefusedPaymentException(String m) {
        super(m);
    }
}