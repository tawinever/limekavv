package com.example.survey.withdrawal.wooppay.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.survey.withdrawal.exception.RefusedPaymentException;
import com.example.survey.withdrawal.model.wallet.MobileWallet;
import com.example.survey.withdrawal.wooppay.RestOptionsProvider;
import com.example.survey.withdrawal.wooppay.mapper.MobileWalletWooppayMapper;
import com.example.survey.withdrawal.wooppay.model.Process;
import com.example.survey.withdrawal.wooppay.model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Map;

import static com.example.survey.withdrawal.wooppay.WooppayGlobals.checkUrl;
import static com.example.survey.withdrawal.wooppay.WooppayGlobals.payUrl;

@Slf4j
@Service
public class MobilePaymentService {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    RestOptionsProvider restOptionsProvider;

    @Autowired
    ObjectMapper objectMapper;

    public CheckResponse checkPayment(MobileWallet mobileWallet, float amount, AccessToken accessToken) throws RefusedPaymentException {
        ResponseEntity<String> response = makeRequest(
                checkUrl,
                getPaymentOptions(accessToken, mobileWallet, amount,null));
        return handleCheckResponse(response);
    }

    private CheckResponse handleCheckResponse(ResponseEntity<String> response) throws RefusedPaymentException {
        if (response.getStatusCode() != HttpStatus.OK) {
            throw new RefusedPaymentException("Payment was refused. Server response code: " + response.getStatusCodeValue());
        }
        try {
            return objectMapper.readValue(response.getBody(), CheckResponse.class);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RefusedPaymentException("Cannot Parse Txn ID. Server response code: \n" + response.getBody());
        }
    }

    public Process executePayment(MobileWallet mobileWallet, float amount, AccessToken accessToken, Txn txn) throws RefusedPaymentException {
        ResponseEntity<String> response = makeRequest(
                payUrl,
                getPaymentOptions(accessToken, mobileWallet, amount, txn));
        return handlePaymentResponse(response);
    }

    private Process handlePaymentResponse(ResponseEntity<String> response) throws RefusedPaymentException {
        if (response.getStatusCode() != HttpStatus.OK) {
            throw new RefusedPaymentException("Payment was refused. Server response code: " + response.getStatusCodeValue());
        }
        try {
            return objectMapper.readValue(response.getBody(), Process.class);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RefusedPaymentException("Cannot Parse Operation object. Server response code: \n" + response.getBody());
        }
    }

    private HttpEntity<Map<String, Object>> getPaymentOptions(AccessToken token, MobileWallet wallet, float amount, Txn txn) {
        MobileWalletWooppay walletWooppay = MobileWalletWooppayMapper.convertFrom(wallet);
        return restOptionsProvider.getOptions(
                MobileWalletWooppayMapper.convertIntoJSON(walletWooppay, amount, txn),
                token
        );
    }

    private ResponseEntity<String> makeRequest(String url, HttpEntity<Map<String, Object>> options) throws RefusedPaymentException {
        try {
            return restTemplate.postForEntity(
                    url,
                    options,
                    String.class
            );
        } catch (Exception e) {
            e.printStackTrace();
            throw new RefusedPaymentException("Cannot execute rest request or error throwed in response");
        }

    }
}
