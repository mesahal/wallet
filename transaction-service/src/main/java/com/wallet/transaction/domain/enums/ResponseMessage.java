package com.wallet.transaction.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ResponseMessage {

    OPERATION_SUCCESSFUL(ApiResponseCode.OPERATION_SUCCESSFUL.getCode(),"operation.successful"),
    SENDER_ACCOUNT_NOT_FOUND(ApiResponseCode.SENDER_ACCOUNT_NOT_FOUND.getCode(),"sender.account.not.found"),
    RECEIVER_ACCOUNT_NOT_FOUND(ApiResponseCode.RECEIVER_ACCOUNT_NOT_FOUND.getCode(),"receiver.account.not.found"),
    ACCOUNT_NOT_FOUND(ApiResponseCode.ACCOUNT_NOT_FOUND.getCode(),"account.not.found"),;

    private final String code;
    private final String messageKey;
}
