package com.example.survey.withdrawal;

import com.example.survey.withdrawal.exception.CannotAuthenticateException;
import com.example.survey.withdrawal.exception.RefusedPaymentException;
import com.example.survey.withdrawal.model.response.PaymentProcessing;
import com.example.survey.withdrawal.model.wallet.MobileWallet;

public interface WithdrawalService {

    public PaymentProcessing sendMoney(MobileWallet mobileWallet, float amount) throws CannotAuthenticateException, RefusedPaymentException;

}
