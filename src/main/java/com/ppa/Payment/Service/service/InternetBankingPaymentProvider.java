package com.ppa.Payment.Service.service;

import com.ppa.Payment.Service.model.Payment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class InternetBankingPaymentProvider implements IPaymentProvider{
    @Override
    public Boolean processPayment(Payment payment) {

        // todo : create dummy api
       // new RestTemplate().getForObject(url, BankResponse.class);
        return true;
    }

    @Override
    public Boolean refundPayment(Payment payment) {
        return true;
    }
}
