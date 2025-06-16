package com.jphilips.library.apigateway.config;

import com.jphilips.library.apigateway.config.routebuilder.RouteBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class GatewayRoutesConfig {

    private final String authServiceUri;
    private final String bookServiceUri;
    private final RouteBuilder routeBuilder;

    @Autowired
    public GatewayRoutesConfig(RouteBuilder routeBuilder,
                               @Value("${AUTH_SERVICE_URI}") String authServiceUri,
                               @Value("${BOOK_SERVICE_URI}") String bookServiceUri) {
        this.routeBuilder = routeBuilder;
        this.authServiceUri = authServiceUri;
        this.bookServiceUri = bookServiceUri;
    }


    @Bean
    public RouteLocator authServiceRoutes(RouteLocatorBuilder builder) {

        RouteLocatorBuilder.Builder routes = builder.routes();

        // Public route (no filter)
        routeBuilder.addPublicRoute(routes,"auth-service-auth-route", "/auth/**",authServiceUri);

        // ----Secured-routes----
        // Auth service
        routeBuilder.addAdminRoute(routes, "auth-service-admin-route", "/admin/auth/users/**", authServiceUri);
        routeBuilder.addUserRoute(routes, "auth-service-user-route", "/users/**", authServiceUri);

        // Book service
        routeBuilder.addAdminRoute(routes, "book-service-admin-route", "/admin/books/**", bookServiceUri);
        routeBuilder.addUserRoute(routes, "book-service-user-route", "/books/**", bookServiceUri);

        return routes.build();
    }

}
