package com.wallet.charge_and_fee_service.service.Impl;

import com.wallet.charge_and_fee_service.common.utils.ChargeAndFeeConstants;
import com.wallet.charge_and_fee_service.domain.response.ChargeAndFeeResponse;
import com.wallet.charge_and_fee_service.service.ChargeAndFeeService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class ChargeAndFeeServiceImpl implements ChargeAndFeeService {

    public ChargeAndFeeResponse getChargeAndFee(BigDecimal amount) {
        ChargeAndFeeResponse chargeAndFeeResponse = new ChargeAndFeeResponse();
        chargeAndFeeResponse.setChargeAmount(getCharge(amount));
        chargeAndFeeResponse.setChargeRate(ChargeAndFeeConstants.ChargeRate);
        return chargeAndFeeResponse;
    }

    private BigDecimal getCharge(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            return BigDecimal.ZERO;
        }
        return amount
                .multiply(ChargeAndFeeConstants.ChargeRate)
                .divide(BigDecimal.valueOf(100),2,RoundingMode.HALF_UP);

    }
}
