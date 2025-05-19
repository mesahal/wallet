package com.wallet.transaction.domain.response;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class TransactionHistoryResponse {
    private String transactionId;
    private String senderUsername;
    private String receiverUsername;
    private BigDecimal amount;
    private LocalDateTime createdAt;
    private String type;
}
