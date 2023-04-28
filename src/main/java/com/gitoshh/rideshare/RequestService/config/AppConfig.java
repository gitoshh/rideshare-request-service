package com.gitoshh.rideshare.RequestService.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;

@Component
public class AppConfig {

    @Value("${server.allowed_origins}")
    private String allowedOrigins = "http://localhost:3000";

    @Bean
    @LoadBalanced
    public WebClient.Builder webClient() {
        return WebClient.builder();
    }

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    /**
     * Bean for CorsFilter
     * @return CorsFilter
     */
    @Bean
    public CorsFilter corsFilter() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration configuration = new CorsConfiguration();
        String[] allowedOriginsArrays = allowedOrigins.split(",");

        configuration.setAllowCredentials(true);
        for (String allowedOrigin: allowedOriginsArrays) {
            configuration.addAllowedOrigin(allowedOrigin);
        }
        configuration.addAllowedHeader("*");

        String[] allowedMethods = {"OPTIONS", "HEAD", "GET", "PUT", "POST", "DELETE", "PATCH"};
        for (String allowedMethod: allowedMethods) {
            configuration.addAllowedMethod(allowedMethod);
        }
        source.registerCorsConfiguration("/**", configuration);
        return new CorsFilter(source);
    }
}

