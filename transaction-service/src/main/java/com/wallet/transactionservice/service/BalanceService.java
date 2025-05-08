package com.wallet.transactionservice.service;


import com.wallet.transactionservice.domain.common.ApiResponse;
import com.wallet.transactionservice.domain.response.BalanceResponse;

public interface BalanceService {

    ApiResponse<BalanceResponse> showBalance(String username);
}
