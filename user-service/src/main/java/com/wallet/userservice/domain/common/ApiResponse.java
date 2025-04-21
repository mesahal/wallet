package com.wallet.userservice.domain.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> implements Serializable {

    private String message;
    private String code;
    private T data;

    public ApiResponse(T data) {
        this.data = data;
    }
}
