package com.wallet.edgeserver.config;

import com.wallet.edgeserver.filter.AuthenticationFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator walletRouteConfig(RouteLocatorBuilder builder, AuthenticationFilter filter) {
        return builder.routes()
                .route(p-> p
                        .path("/wallet/api/v1/user/**")
                        .filters(f-> f
                                .filter(filter.apply(new AuthenticationFilter.Config()))
                                .rewritePath("/wallet/(?<segment>.*)","/${segment}")
                                .addResponseHeader("X-Response-Time", LocalDateTime.now().toString()))
                        .uri("lb://USERS"))
                .route(p-> p
                        .path("/wallet/api/v1/transaction/**")
                        .filters(f-> f
                                .filter(filter.apply(new AuthenticationFilter.Config()))
                                .rewritePath("/wallet/(?<segment>.*)","/${segment}")
                                .addResponseHeader("X-Response-Time", LocalDateTime.now().toString()))
                        .uri("lb://TRANSACTIONS"))
                .route(p-> p
                        .path("/wallet/api/v1/chargeandfee/**")
                        .filters(f-> f
                                .filter(filter.apply(new AuthenticationFilter.Config()))
                                .rewritePath("/wallet/(?<segment>.*)","/${segment}")
                                .addResponseHeader("X-Response-Time", LocalDateTime.now().toString()))
                        .uri("lb://CHARGE-AND-FEE"))
                .build();
    }
}
