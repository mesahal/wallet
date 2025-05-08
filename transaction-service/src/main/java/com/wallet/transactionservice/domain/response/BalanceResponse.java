package com.wallet.transactionservice.domain.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class BalanceResponse {
    private BigDecimal balance;
}
