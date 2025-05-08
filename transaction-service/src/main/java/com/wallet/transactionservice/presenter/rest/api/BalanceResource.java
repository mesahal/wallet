package com.wallet.transactionservice.presenter.rest.api;

import com.wallet.transactionservice.common.AppUtils;
import com.wallet.transactionservice.domain.common.ApiResponse;
import com.wallet.transactionservice.domain.response.BalanceResponse;
import com.wallet.transactionservice.service.BalanceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(AppUtils.BASE_URL)
public class BalanceResource {

    private final BalanceService balanceService;

    public BalanceResource(BalanceService balanceService) {
        this.balanceService = balanceService;
    }

    @GetMapping("/balance/{username}")
    public ResponseEntity<ApiResponse<BalanceResponse>> balance(@PathVariable String username) {
        ApiResponse<BalanceResponse> response = balanceService.showBalance(username);
        return ResponseEntity.ok(response);
    }
}
