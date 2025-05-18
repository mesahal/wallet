package com.wallet.charge_and_fee_service.presenter.rest.api;

import com.wallet.charge_and_fee_service.common.utils.AppUtils;
import com.wallet.charge_and_fee_service.domain.common.ApiResponse;
import com.wallet.charge_and_fee_service.domain.response.ChargeAndFeeResponse;
import com.wallet.charge_and_fee_service.service.Impl.ChargeAndFeeServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping(AppUtils.BASE_URL)
@RequiredArgsConstructor
public class ChargeAndFeeResource {

    private final ChargeAndFeeServiceImpl chargeAndFeeService;

    @GetMapping("/{amount}")
    public ApiResponse<ChargeAndFeeResponse> chargeAndFee(@PathVariable BigDecimal amount) {
        return new ApiResponse<>("200","OK",chargeAndFeeService.getChargeAndFee(amount));
    }
}
