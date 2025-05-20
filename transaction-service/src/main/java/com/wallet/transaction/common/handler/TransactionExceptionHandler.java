package com.wallet.transaction.common.handler;

import com.wallet.transaction.common.exceptions.ResourceNotFoundException;
import com.wallet.transaction.domain.common.ApiResponse;
import com.wallet.transaction.service.LocaleMessageService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RequiredArgsConstructor
@RestControllerAdvice
public class TransactionExceptionHandler extends BaseExceptionHandler {

    private final LocaleMessageService localeMessageService;


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<Object>> handleException(ResourceNotFoundException exception) {
        errorLogger.error(exception.getMessage(), exception);
        ApiResponse<Object> apiResponse = buildApiResponse(HttpStatus.NOT_FOUND.toString(), getMessage(exception.getMessage()),null);
        return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }

    private String getMessage(String messageKey) {
        String message = StringUtils.EMPTY;

        try {
            message = localeMessageService.getLocalMessage(messageKey);
        } catch (Exception ex) {
            logger.error(ex.getLocalizedMessage(), ex);
        }

        return StringUtils.isNotBlank(message) ? message : messageKey;
    }

}
