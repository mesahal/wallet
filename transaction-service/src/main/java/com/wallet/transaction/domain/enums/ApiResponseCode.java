package com.wallet.transaction.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ApiResponseCode {

    OPERATION_SUCCESSFUL("200"),
    SENDER_ACCOUNT_NOT_FOUND("400"),
    RECEIVER_ACCOUNT_NOT_FOUND("404"),
    ACCOUNT_ALREADY_EXISTS("409"),
    ACCOUNT_NOT_FOUND("404"),;

    private final String code;


}
