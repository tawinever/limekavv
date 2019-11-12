package com.example.survey.withdrawal.wooppay;

public class WooppayGlobals {
    public static final String login = "test_subagent";
    public static final String password = "A12345678a";

    private static final String apiDomain = "https://api.yii2-stage.test.wooppay.com";
    public static final String authUrl = apiDomain + "/v1/auth";
    public static final String checkUrl = apiDomain + "/v1/payment/check";
    public static final String payUrl = apiDomain + "/v1/payment/pay-from-wallet";


}
