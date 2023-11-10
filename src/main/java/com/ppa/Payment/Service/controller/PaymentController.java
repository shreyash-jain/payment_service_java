package com.ppa.Payment.Service.controller;

import com.ppa.Payment.Service.dto.PaymentDto;
import com.ppa.Payment.Service.manager.PaymentManager;
import com.ppa.Payment.Service.model.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("payment")
public class PaymentController {

    @Autowired
    PaymentManager paymentManager;



    @RequestMapping(method = RequestMethod.POST, path = "/transfer")
    public ResponseEntity<Payment> transferAmount(@RequestBody PaymentDto paymentDto){
        return ResponseEntity.ok().body(paymentManager.processPayment(paymentDto));
    }


}
