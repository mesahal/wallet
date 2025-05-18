package com.wallet.charge_and_fee_service.domain.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class ApiResponse<T> implements Serializable {

    private String message;
    private String code;
    private T data;

}
