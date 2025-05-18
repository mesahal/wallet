package com.wallet.transactionservice.domain.entity;

import com.wallet.transactionservice.domain.enums.TransactionType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "transaction_history")
public class TransactionHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String transactionId;
    private String senderUsername;
    private String receiverUsername;
    private BigDecimal amount;
    private LocalDateTime createdAt;
    private Long type;

}
