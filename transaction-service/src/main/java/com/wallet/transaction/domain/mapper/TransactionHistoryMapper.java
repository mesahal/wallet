package com.wallet.transaction.domain.mapper;

import com.wallet.transaction.domain.entity.TransactionHistory;
import com.wallet.transaction.domain.response.TransactionHistoryResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TransactionHistoryMapper {
    TransactionHistoryResponse toTransactionHistoryResponse(TransactionHistory transactions);
}
