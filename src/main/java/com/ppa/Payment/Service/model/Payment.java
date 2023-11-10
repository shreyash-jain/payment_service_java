package com.ppa.Payment.Service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "payment_system")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Payment {

    String status;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Long senderAccountId;
    Long receiverAccountId;
    Double amount;
    String type;

}
