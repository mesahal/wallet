package com.wallet.edgeserver.domain.common;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class ApiResponse<T>  implements Serializable {

    private String message;
    private String code;
    private T data;

    public ApiResponse(String code, String message, T data) {
        this.data = data;
        this.message = message;
        this.code = code;
    }
}
