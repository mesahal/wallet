package com.wallet.transaction.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@Entity
@Getter
@Setter
@Table(name = "transaction_history")
@RequiredArgsConstructor
@AllArgsConstructor
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
