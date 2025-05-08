package com.wallet.transactionservice.domain.request;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class AddMoneyRequest {
    private String username;
    private BigDecimal amount;
}
