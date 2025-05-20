package com.wallet.transaction.domain.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Builder
@Getter
@Setter
public class TransactionResponse {
    private String senderAccount;
    private String receiverAccount;
    private String transactionId;
    private BigDecimal amount;

}
