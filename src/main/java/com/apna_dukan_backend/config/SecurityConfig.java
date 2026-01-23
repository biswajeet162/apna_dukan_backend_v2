package com.apna_dukan_backend.config;

import com.apna_dukan_backend.auth.exception.SecurityExceptionHandler;
import com.apna_dukan_backend.auth.filter.JwtAuthenticationFilter;
import com.apna_dukan_backend.auth.service.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final UserDetailsServiceImpl userDetailsService;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final SecurityExceptionHandler securityExceptionHandler;
    private final CorsFilter corsFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        // ============================================
                        // PUBLIC APIs (JWT Optional - permitAll)
                        // ============================================
                        // Authentication endpoints
                        .requestMatchers("/api/auth/**").permitAll()
                        .requestMatchers("/api/v1/auth/**").permitAll()
                        
                        // Health check
                        .requestMatchers("/api/v1/health/**", "/api/health").permitAll()
                        
                        // Swagger/API docs
                        .requestMatchers("/swagger-ui/**", "/swagger-ui.html", "/api-docs/**", "/v3/api-docs/**").permitAll()
                        .requestMatchers("/h2-console/**").permitAll()
                        
                        // Public catalog APIs (JWT optional - will be set if token present)
                        .requestMatchers("/api/v1/layout/**").permitAll()
                        .requestMatchers("/api/v1/section/**").permitAll()
                        .requestMatchers("/api/v1/category/**").permitAll()
                        .requestMatchers("/api/v1/subCategory/**").permitAll()
                        .requestMatchers("/api/v1/productGroup/**").permitAll()
                        .requestMatchers("/api/v1/product/**").permitAll()
                        .requestMatchers("/api/v1/products/**").permitAll()
                        .requestMatchers("/api/variants/**").permitAll()
                        
                        // Public user catalog endpoint (accessible to guests, token optional)
                        .requestMatchers("/api/user/catalog/layout").permitAll()
                        
                        // ============================================
                        // USER APIs (JWT Required - ROLE_USER)
                        // ============================================
                        .requestMatchers("/api/user/**").hasAuthority("ROLE_USER")
                        .requestMatchers("/api/cart/**").hasAuthority("ROLE_USER")
                        .requestMatchers("/api/order/**").hasAuthority("ROLE_USER")
                        .requestMatchers("/api/review/**").hasAuthority("ROLE_USER")
                        
                        // ============================================
                        // ADMIN APIs (JWT Required - ROLE_ADMIN)
                        // ============================================
                        // Strictly require ROLE_ADMIN (SYSTEM can also access)
                        .requestMatchers("/api/admin/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_SYSTEM")
                        
                        // Legacy admin endpoints
                        .requestMatchers("/api/v1/admin/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_SYSTEM")
                        
                        // ============================================
                        // SYSTEM APIs (JWT Required - ROLE_SYSTEM)
                        // ============================================
                        .requestMatchers("/api/system/**").hasAuthority("ROLE_SYSTEM")
                        
                        // ============================================
                        // All other requests require authentication
                        // ============================================
                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .exceptionHandling(exceptions -> exceptions
                        .authenticationEntryPoint(securityExceptionHandler)
                        .accessDeniedHandler(securityExceptionHandler)
                )
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class) // Add CORS filter before JWT filter
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .headers(headers -> headers.frameOptions(frameOptions -> frameOptions.sameOrigin()));

        return http.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

