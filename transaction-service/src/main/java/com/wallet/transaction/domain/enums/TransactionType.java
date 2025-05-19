package com.wallet.transaction.domain.enums;

import lombok.Getter;

@Getter
public enum TransactionType {
    ADD_MONEY(1L,"Add money"),
    TRANSFER_MONEY(2L,"Transfer money"),
    WITHDRAW_MONEY(3L,"Withdraw money"),;

    private Long code;
    private String description;

    TransactionType(Long code, String description) {
        this.code = code;
        this.description = description;
    }

    public static String getTransactionType(Long code) {
        for (TransactionType transactionType : TransactionType.values()) {
            if (transactionType.getCode().equals(code)) {
                return transactionType.getDescription();
            }
        }
        throw new IllegalArgumentException("No transaction type with code " + code);
    }
}
