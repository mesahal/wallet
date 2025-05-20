package com.wallet.transaction.service;

import com.wallet.transaction.domain.enums.ResponseMessage;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BaseService {

    protected final LocaleMessageService localeMessageService;

    public String getMessage(ResponseMessage key) {
        return localeMessageService.getLocalMessage(key);
    }

    public String getMessage(String key, Object... args) {
        return localeMessageService.getLocalMessage(key, args);
    }

}
