package com.example.survey.withdrawal.wooppay.mapper;

import com.example.survey.withdrawal.model.wallet.MobileWallet;
import com.example.survey.withdrawal.wooppay.model.MobileWalletWooppay;
import com.example.survey.withdrawal.wooppay.model.Txn;

import java.util.HashMap;
import java.util.Map;

public class MobileWalletWooppayMapper {

    public static MobileWalletWooppay convertFrom(MobileWallet mobileWallet) {
        MobileWalletWooppay mww = new MobileWalletWooppay();
        mww.setPhoneNumber(mobileWallet.getPhoneNumber());
        switch (mobileWallet.getOperator()) {
            case KCELL:
                mww.setServiceName("kcell");
                mww.setServiceId(1421);
                break;
            case ACTIV:
                mww.setServiceName("activ");
                mww.setServiceId(1798);
                break;
            case ALTEL:
                mww.setServiceName("altel_4G");
                mww.setServiceId(1997);
                break;
            case BEELINE:
                mww.setServiceName("beeline");
                mww.setServiceId(958);
                break;
        }
        return mww;
    }

    public static Map<String, Object> convertIntoJSON(MobileWalletWooppay mobileWalletWooppay, float amount, Txn txn) {
        Map<String, Object> transfer = new HashMap<>();
        Map<String, Object> field = new HashMap<>();
        field.put("account", mobileWalletWooppay.getPhoneNumber());
        field.put("amount", amount);
        if (txn != null)
            field.put("txn_id", txn.getTxn_id());
        transfer.put("service_id", mobileWalletWooppay.getServiceId());
        transfer.put("service_name", mobileWalletWooppay.getServiceName());
        transfer.put("fields", field);
        return transfer;
    }
}
