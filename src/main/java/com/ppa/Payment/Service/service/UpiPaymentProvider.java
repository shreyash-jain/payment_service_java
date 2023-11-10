package com.ppa.Payment.Service.service;

import com.ppa.Payment.Service.model.Payment;
import org.springframework.stereotype.Service;


@Service
public class UpiPaymentProvider implements IPaymentProvider{
    @Override
    public Boolean processPayment(Payment payment) {
        return null;
    }

    @Override
    public Boolean refundPayment(Payment payment) {
        return null;
    }
}
