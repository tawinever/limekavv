package com.example.survey.withdrawal.exception;

public class ServiceBusyException extends Throwable {
    public ServiceBusyException(String m) {
        super(m);
    }
}