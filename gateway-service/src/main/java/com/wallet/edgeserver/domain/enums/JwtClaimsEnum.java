package com.wallet.edgeserver.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum JwtClaimsEnum {
    USER_IDENTITY("username");
    private String claim;
}
