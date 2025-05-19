package com.wallet.transaction.service;

import com.wallet.transaction.domain.common.ApiResponse;
import com.wallet.transaction.domain.request.AddMoneyRequest;
import com.wallet.transaction.domain.request.TransferMoneyRequest;
import com.wallet.transaction.domain.response.TransactionHistoryResponse;
import com.wallet.transaction.domain.response.TransactionResponse;

import java.util.List;

public interface TransactionService {
    ApiResponse<TransactionResponse> addMoney(AddMoneyRequest request);

    ApiResponse<TransactionResponse> transferMoney(TransferMoneyRequest request);

    List<TransactionHistoryResponse> transactionHistory(String username);
}
