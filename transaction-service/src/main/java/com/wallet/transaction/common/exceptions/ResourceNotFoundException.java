package com.wallet.transaction.common.exceptions;

import com.wallet.transaction.domain.enums.ResponseMessage;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(ResponseMessage responseMessage) {
        super(responseMessage.getMessageKey());
    }
}


