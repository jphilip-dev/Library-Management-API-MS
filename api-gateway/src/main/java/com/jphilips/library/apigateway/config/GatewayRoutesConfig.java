package com.jphilips.library.apigateway.config;

import com.jphilips.library.apigateway.config.routebuilder.RouteBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@RequiredArgsConstructor
public class GatewayRoutesConfig {

    @Value("${AUTH_SERVICE_URI}")
    private final String authServiceUri;

    private final RouteBuilder routeBuilder;

    @Bean
    public RouteLocator authServiceRoutes(RouteLocatorBuilder builder) {

        RouteLocatorBuilder.Builder routes = builder.routes();

        // Public route (no filter)
        routeBuilder.addPublicRoute(routes,"auth-service-auth-route", "/auth/**",authServiceUri);

        // Secured routes
        routeBuilder.addAdminRoute(routes, "auth-service-admin-route", "/admin/**", authServiceUri);
        routeBuilder.addUserRoute(routes, "auth-service-user-route", "/users/**", authServiceUri);

        return routes.build();
    }
}
