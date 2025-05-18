package com.wallet.transactionservice.domain.mapper;

import com.wallet.transactionservice.domain.entity.TransactionHistory;
import com.wallet.transactionservice.domain.response.TransactionHistoryResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TransactionHistoryMapper {
    TransactionHistoryResponse toTransactionHistoryResponse(TransactionHistory transactions);
}
