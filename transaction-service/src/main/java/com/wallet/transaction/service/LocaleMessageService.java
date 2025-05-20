package com.wallet.transaction.service;


import com.wallet.transaction.domain.enums.ResponseMessage;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
@RequiredArgsConstructor
public class LocaleMessageService {

    private final HttpServletRequest request;
    private final MessageSource messageSource;

    public Locale getLocale() {
        try {
            return request.getLocale();
        } catch (Exception ex) {
            return Locale.getDefault();
        }
    }

    public String getLocalMessage(ResponseMessage key, Object... objects) {
        return messageSource.getMessage(key.getMessageKey(), objects, getLocale());
    }

    public String getLocalMessage(ResponseMessage key) {
        return messageSource.getMessage(key.getMessageKey(), null, getLocale());
    }

    public String getLocalMessage(String key, Object... objects) {
        return messageSource.getMessage(key, objects, getLocale());
    }

    public String getLocalMessage(String key) {
        return messageSource.getMessage(key, null, getLocale());
    }


}

