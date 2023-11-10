package com.ppa.Payment.Service.service;

import com.ppa.Payment.Service.dto.PaymentDto;
import com.ppa.Payment.Service.model.Payment;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    public Payment createInitalPayment(PaymentDto paymentDto) {
        // validations

        return Payment
                .builder()
                .amount(paymentDto.getAmount())
                .type(paymentDto.getType())
                .senderAccountId(paymentDto.getSenderId())
                .receiverAccountId(paymentDto.getReceiverId())
                .status("INIT")
                .build();
    }
}
