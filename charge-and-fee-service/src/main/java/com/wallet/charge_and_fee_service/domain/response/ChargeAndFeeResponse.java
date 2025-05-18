package com.wallet.charge_and_fee_service.domain.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ChargeAndFeeResponse {
    private BigDecimal chargeAmount;
    private BigDecimal chargeRate;
}
