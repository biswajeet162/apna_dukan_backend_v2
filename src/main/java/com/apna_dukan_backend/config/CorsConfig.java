package com.apna_dukan_backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        
        // Allow all origins for development
        // In production, replace with specific frontend URLs:
        // config.setAllowedOrigins(Arrays.asList("https://yourdomain.com", "https://www.yourdomain.com"));
        config.addAllowedOriginPattern("*");
        
        // Allow all HTTP methods
        config.addAllowedMethod("*");
        
        // Allow all headers
        config.addAllowedHeader("*");
        
        // Expose all headers in response
        config.addExposedHeader("*");
        
        // Allow credentials (cookies, authorization headers)
        // Set to true if you need to send cookies/credentials
        config.setAllowCredentials(false);
        
        // Cache preflight requests for 1 hour
        config.setMaxAge(3600L);
        
        // Apply CORS configuration to all API endpoints
        source.registerCorsConfiguration("/api/**", config);
        return new CorsFilter(source);
    }
}

