package com.wallet.edgeserver.handler;

import com.wallet.edgeserver.domain.common.ApiResponse;
import org.slf4j.LoggerFactory;
import org.springframework.web.reactive.result.method.annotation.ResponseEntityExceptionHandler;

import org.slf4j.Logger;

public class BaseExceptionHandler extends ResponseEntityExceptionHandler {

    protected static final Logger errorLogger = LoggerFactory.getLogger("errorLogger");

    protected ApiResponse<Void> buildApiResponse(String messageCode, String message) {
        ApiResponse<Void> apiResponse = new ApiResponse<>();
        apiResponse.setMessage(message);
        apiResponse.setCode(messageCode);
        return apiResponse;
    }

    protected ApiResponse<Object> buildApiResponse(String messageCode, String message, Object data) {
        ApiResponse<Object> apiResponse = new ApiResponse<>();
        apiResponse.setMessage(message);
        apiResponse.setCode(messageCode);
        apiResponse.setData(data);
        return apiResponse;
    }
}
