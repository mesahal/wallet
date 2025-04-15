package com.wallet.edgeserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EdgeserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(EdgeserverApplication.class, args);
	}

	@Bean
	public RouteLocator walletRouteConfig(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(p-> p
						.path("/wallet/**")
						.filters(f-> f
								.rewritePath("/wallet/(?<segment>.*)","/${segment}"))
						.uri("lb://USERS"))
				.build();
	}

}
