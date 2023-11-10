package com.ppa.Payment.Service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jdk.jfr.DataAmount;
import lombok.Data;


@Data
public class PaymentDto {

    @JsonProperty("sender_id")
    private Long senderId;
    @JsonProperty("receiver_id")
    private Long receiverId;
    private Double amount;
    private String type;


}
