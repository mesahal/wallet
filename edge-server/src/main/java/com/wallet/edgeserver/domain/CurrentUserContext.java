package com.wallet.edgeserver.domain;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class CurrentUserContext implements Serializable {
    private String userIdentity;
}
