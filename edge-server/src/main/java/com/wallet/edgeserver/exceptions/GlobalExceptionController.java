package com.wallet.edgeserver.exceptions;

import com.wallet.edgeserver.domain.common.ApiResponse;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionController {

    @ExceptionHandler(MissingAuthorizationHeaderException.class)
    public ResponseEntity<ApiResponse<Void>> handleException(MissingAuthorizationHeaderException ex) {
        ApiResponse<Void> apiResponse = new ApiResponse<>(HttpStatus.UNAUTHORIZED.toString(), ex.getMessage(), null);
        return new ResponseEntity<>(apiResponse, HttpStatus.UNAUTHORIZED);
    }

//    @ExceptionHandler(ExpiredJwtException.class)
//    public ResponseEntity<ApiResponse<Void>> handleException(ExpiredJwtException ex) {
//        ApiResponse<Void> apiResponse = new ApiResponse<>(HttpStatus.UNAUTHORIZED.toString(), ex.getMessage(), null);
//        return new ResponseEntity<>(apiResponse, HttpStatus.UNAUTHORIZED);
//    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleException(Exception ex) {
        ApiResponse<Void> apiResponse = new ApiResponse<>(HttpStatus.UNAUTHORIZED.toString(), ex.getMessage(), null);
        return new ResponseEntity<>(apiResponse, HttpStatus.UNAUTHORIZED);
    }
}
