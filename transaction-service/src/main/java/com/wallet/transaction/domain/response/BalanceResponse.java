package com.wallet.transaction.domain.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Builder
@Getter
@Setter
public class BalanceResponse {
    private BigDecimal balance;
}
