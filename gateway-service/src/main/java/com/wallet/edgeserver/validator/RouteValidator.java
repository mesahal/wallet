package com.wallet.edgeserver.validator;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.function.Predicate;
import java.util.stream.Stream;

@Component
public class RouteValidator {

    public Predicate<ServerHttpRequest> isSecured = request ->
            Stream.of("/wallet/api/v1/user/signup","/wallet/api/v1/user/signin")
                    .noneMatch(uri -> request.getURI().getPath().contains(uri));
}
