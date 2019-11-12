package com.example.survey.withdrawal.wooppay;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.survey.withdrawal.WithdrawalService;
import com.example.survey.withdrawal.exception.CannotAuthenticateException;
import com.example.survey.withdrawal.exception.RefusedPaymentException;
import com.example.survey.withdrawal.model.response.PaymentProcessing;
import com.example.survey.withdrawal.model.wallet.MobileWallet;
import com.example.survey.withdrawal.wooppay.Service.AuthenticationService;
import com.example.survey.withdrawal.wooppay.Service.MobilePaymentService;
import com.example.survey.withdrawal.wooppay.mapper.ProcessMapper;
import com.example.survey.withdrawal.wooppay.model.AccessToken;
import com.example.survey.withdrawal.wooppay.model.CheckResponse;
import com.example.survey.withdrawal.wooppay.model.Process;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class WooppayFacade implements WithdrawalService {
    private static String login = "test_subagent";
    private static String password = "A12345678aq";
    private static String apiDomain = "https://api.yii2-stage.test.wooppay.com";


    @Autowired
    RestTemplate restTemplate;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    MobilePaymentService mobilePaymentService;

    @Override
    public PaymentProcessing sendMoney(MobileWallet mobileWallet, float amount) throws CannotAuthenticateException, RefusedPaymentException {
        //auth to wooppay
        AccessToken token = authenticationService.authenticate();
        log.info("Access token : {}", token.getToken() );

        //checkPayment to mobile wallet
        CheckResponse checkResponse = mobilePaymentService.checkPayment(mobileWallet, amount, token);
        log.info("Txn id : {}", checkResponse.getFields().getTxn_id());

        //doPayment
        Process process = mobilePaymentService.executePayment(mobileWallet, amount, token, checkResponse.getFields());
        log.info("Process operation id: {}", process.getOperation().getId());

        log.info("Send {} KZT to {} with operator - {} ", amount, mobileWallet.getPhoneNumber(), mobileWallet.getOperator().toString());
        return ProcessMapper.convertIntoPaymentProcess(process);
    }
}
