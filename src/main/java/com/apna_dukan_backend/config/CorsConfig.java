package com.apna_dukan_backend.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@Configuration
public class CorsConfig {

    @Value("${spring.profiles.active:default}")
    private String activeProfile;

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        
        // Production frontend URL
        String productionFrontend = "https://apna-dukan-frontend-v2.onrender.com";
        
        // Default to development mode if profile is not explicitly set to "prod"
        boolean isProduction = "prod".equals(activeProfile);
        
        if (isProduction) {
            // In production, allow specific frontend domain
            config.setAllowedOrigins(Arrays.asList(
                productionFrontend
            ));
            config.setAllowCredentials(true); // Allow credentials in production
        } else {
            // In development, allow localhost origins with credentials
            // Note: Cannot use wildcard "*" with credentials, so we use origin patterns
            // This allows any localhost port (Flutter web typically runs on random ports)
            config.addAllowedOriginPattern("http://localhost:*");
            config.addAllowedOriginPattern("http://127.0.0.1:*");
            config.addAllowedOriginPattern("http://[::1]:*"); // IPv6 localhost
            config.setAllowCredentials(true); // Allow credentials in development
        }
        
        // Allow all HTTP methods
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"));
        
        // Allow all headers (including Authorization)
        config.setAllowedHeaders(Arrays.asList("*"));
        
        // Expose common headers in response
        config.setExposedHeaders(Arrays.asList(
            "Authorization",
            "Content-Type",
            "X-Total-Count",
            "Access-Control-Allow-Origin",
            "Access-Control-Allow-Credentials"
        ));
        
        // Cache preflight requests for 1 hour
        config.setMaxAge(3600L);
        
        // Apply CORS configuration to all API endpoints
        source.registerCorsConfiguration("/api/**", config);
        return new CorsFilter(source);
    }
}

