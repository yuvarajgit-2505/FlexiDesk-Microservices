package com.flexidesk.api_gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator customRoutes(RouteLocatorBuilder builder) {

        return builder.routes()

                // Auth Service
                .route("auth-service", r -> r
                        .path("/auth/**", "/user/**", "/admin/**")
                        .uri("lb://AUTH-SERVICE"))

                // Booking Service
                .route("booking-service", r -> r
                        .path("/api/bookings/**")
                        .uri("lb://BOOKING-SERVICE"))

                // Desk Service
                .route("desk-service", r -> r
                        .path("/api/desks/**")
                        .uri("lb://DESK-SERVICE"))

                .build();
    }

}
