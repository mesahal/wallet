package com.wallet.charge_and_fee_service.service;

import com.wallet.charge_and_fee_service.domain.response.ChargeAndFeeResponse;

import java.math.BigDecimal;

public interface ChargeAndFeeService {

    public ChargeAndFeeResponse getChargeAndFee(BigDecimal amount);
}
