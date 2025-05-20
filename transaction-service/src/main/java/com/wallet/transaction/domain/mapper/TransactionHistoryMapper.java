package com.wallet.transaction.domain.mapper;

import com.wallet.transaction.domain.entity.TransactionHistory;
import com.wallet.transaction.domain.enums.TransactionType;
import com.wallet.transaction.domain.request.AddMoneyRequest;
import com.wallet.transaction.domain.request.TransferMoneyRequest;
import com.wallet.transaction.domain.response.TransactionHistoryResponse;
import com.wallet.transaction.domain.response.TransactionResponse;
import org.apache.commons.lang3.RandomStringUtils;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class TransactionHistoryMapper {

    public TransactionHistoryResponse toTransactionHistoryResponse(TransactionHistory transactionHistory){
        return TransactionHistoryResponse.builder()
                .transactionId(transactionHistory.getTransactionId())
                .amount(transactionHistory.getAmount())
                .type(TransactionType.getTransactionType(transactionHistory.getType()))
                .createdAt(transactionHistory.getCreatedAt())
                .senderUsername(transactionHistory.getSenderUsername())
                .receiverUsername(transactionHistory.getReceiverUsername())
                .build();
    }

    public TransactionHistory buildTransactionHistoryForTransferMoney(TransferMoneyRequest request) {
        return TransactionHistory.builder()
                .receiverUsername(request.getReceiverUsername())
                .amount(request.getAmount())
                .type(TransactionType.TRANSFER_MONEY.getCode())
                .createdAt(LocalDateTime.now())
                .senderUsername(request.getSenderUsername())
                .transactionId(getTransactionId())
                .build();
    }

    public TransactionHistory buildTransactionHistoryForAddMoney(AddMoneyRequest request) {
        return TransactionHistory.builder()
                .receiverUsername(request.getUsername())
                .amount(request.getAmount())
                .type(TransactionType.TRANSFER_MONEY.getCode())
                .createdAt(LocalDateTime.now())
                .senderUsername("SYSTEM")
                .transactionId(getTransactionId())
                .build();
    }

    private String getTransactionId(){
        return "TXN-" + RandomStringUtils.randomAlphanumeric(12).toUpperCase();
    }
}
