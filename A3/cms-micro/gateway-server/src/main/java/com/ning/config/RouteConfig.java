package com.ning.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouteConfig {
    @Bean
    public RouteLocator customizeRoute(RouteLocatorBuilder builder) {
        return builder
                .routes()
                .route("user-server",
                        r -> r.path("/news-micro/user/**").uri("lb://userserver")
                )
                .route("review-server",
                        r -> r.path("/news-micro/review/**").uri("lb://reviewserver")
                )
                .route("news-server",
                        r -> r.path("/news-micro/news/**").uri("lb://newsserver")
                )
                .route("comment-server",
                        r -> r.path("/news-micro/comment/**").uri("lb://commentserver")
                )
                .route("category-server",
                        r -> r.path("/news-micro/category/**").uri("lb://categoryserver")
                )
                .build();
    }
}
