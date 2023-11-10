package com.ppa.Payment.Service.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PaymentFactory {

    @Autowired
    InternetBankingPaymentProvider internetBankingPaymentService;

    @Autowired
    UpiPaymentProvider upiPaymentProvider;

    public IPaymentProvider getPlan(String paymentType) {
        if (paymentType == null) {
            return null;
        }

        if (paymentType.equalsIgnoreCase("INTERNET_BANKING")) {
            return internetBankingPaymentService;
        }
        return upiPaymentProvider;
    }
}
