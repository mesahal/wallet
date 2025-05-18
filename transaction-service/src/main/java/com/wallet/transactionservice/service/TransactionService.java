package com.wallet.transactionservice.service;

import com.wallet.transactionservice.domain.common.ApiResponse;
import com.wallet.transactionservice.domain.request.AddMoneyRequest;
import com.wallet.transactionservice.domain.request.TransferMoneyRequest;
import com.wallet.transactionservice.domain.response.TransactionHistoryResponse;
import com.wallet.transactionservice.domain.response.TransactionResponse;

import java.util.List;

public interface TransactionService {
    ApiResponse<TransactionResponse> addMoney(AddMoneyRequest request);

    ApiResponse<TransactionResponse> transferMoney(TransferMoneyRequest request);

    List<TransactionHistoryResponse> transactionHistory(String username);
}
