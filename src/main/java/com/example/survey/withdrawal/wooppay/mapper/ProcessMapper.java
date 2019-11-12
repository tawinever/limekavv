package com.example.survey.withdrawal.wooppay.mapper;

import com.example.survey.withdrawal.model.response.PaymentProcessing;
import com.example.survey.withdrawal.wooppay.model.Process;


public class ProcessMapper {
    public static PaymentProcessing convertIntoPaymentProcess(Process process) {
        PaymentProcessing paymentProcessing = new PaymentProcessing();
        paymentProcessing.setProcessId(process.getOperation().getId());
        return paymentProcessing;
    }
}
