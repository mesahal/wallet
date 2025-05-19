package com.wallet.transaction.service;


import com.wallet.transaction.domain.common.ApiResponse;
import com.wallet.transaction.domain.response.BalanceResponse;

public interface BalanceService {

    ApiResponse<BalanceResponse> showBalance(String username);
}
