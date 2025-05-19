package com.wallet.transaction.domain.request;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class TransferMoneyRequest {

    private String senderUsername;
    private String receiverUsername;
    private BigDecimal amount;
}
