package com.ppa.Payment.Service.service;

import com.ppa.Payment.Service.dto.PaymentDto;
import com.ppa.Payment.Service.model.Payment;

public interface IPaymentProvider {

    Boolean processPayment(Payment payment);

    Boolean refundPayment(Payment payment);
}
