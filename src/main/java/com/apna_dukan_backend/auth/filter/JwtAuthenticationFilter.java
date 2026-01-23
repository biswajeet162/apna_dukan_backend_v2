package com.apna_dukan_backend.auth.filter;

import com.apna_dukan_backend.auth.exception.SecurityExceptionHandler;
import com.apna_dukan_backend.auth.service.JwtService;
import com.apna_dukan_backend.auth.service.UserDetailsServiceImpl;
import com.apna_dukan_backend.common.exception.ErrorCode;
import com.apna_dukan_backend.common.exception.dto.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;

/**
 * JWT Authentication Filter
 * 
 * Key Principles:
 * 1. Missing token → Allow request as anonymous (for public APIs)
 * 2. Present but invalid token → Reject with 401 (for ALL APIs, including public)
 * 3. Valid token → Set SecurityContext with user details
 * 
 * This enables:
 * - Public APIs to work without authentication
 * - Public APIs to use JWT context when token is present
 * - Admin APIs to strictly require valid JWT
 * - Consistent security: invalid tokens always rejected
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);
    private final JwtService jwtService;
    private final UserDetailsServiceImpl userDetailsService;
    private final ObjectMapper objectMapper;

    public JwtAuthenticationFilter(JwtService jwtService, UserDetailsServiceImpl userDetailsService) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule());
    }

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");

        // ============================================
        // PRINCIPLE 1: Missing token = Anonymous
        // ============================================
        // If no Authorization header, allow request to proceed as anonymous
        // This enables public APIs (catalog, products) to work without authentication
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // ============================================
        // PRINCIPLE 2: Present token = MUST validate
        // ============================================
        // Authorization header is present - MUST validate JWT
        // Invalid token will be rejected with 401 (even for public APIs)
        final String jwt = authHeader.substring(7);
        
        try {
            // Extract userId from token
            final String userIdStr = jwtService.extractUserId(jwt);
            
            if (userIdStr == null) {
                logger.warn("JWT token missing userId claim");
                sendUnauthorizedResponse(request, response, "Invalid token: missing userId");
                return;
            }

            // Validate token format and expiration
            if (!jwtService.validateToken(jwt)) {
                logger.warn("JWT token validation failed");
                sendUnauthorizedResponse(request, response, "Invalid or expired token");
                return;
            }

            // Load user details and set security context
            if (SecurityContextHolder.getContext().getAuthentication() == null) {
                try {
                    UUID userId = UUID.fromString(userIdStr);
                    UserDetails userDetails = this.userDetailsService.loadUserByUserId(userId);

                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities()
                    );
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                    
                    logger.info("Successfully authenticated user: {} with roles: {}", 
                            userId, userDetails.getAuthorities());
                } catch (UsernameNotFoundException e) {
                    logger.warn("User not found for userId: {}", userIdStr);
                    sendUnauthorizedResponse(request, response, "Invalid token: user not found");
                    return;
                } catch (IllegalArgumentException e) {
                    logger.warn("Invalid userId format: {}", userIdStr);
                    sendUnauthorizedResponse(request, response, "Invalid token: malformed userId");
                    return;
                } catch (Exception e) {
                    logger.error("Unexpected error loading user", e);
                    sendUnauthorizedResponse(request, response, "Invalid token: authentication failed");
                    return;
                }
            }
            
            // Token is valid and user is authenticated - continue filter chain
            filterChain.doFilter(request, response);
            
        } catch (Exception e) {
            // Unexpected error - treat as authentication failure
            logger.error("Unexpected error during JWT authentication", e);
            SecurityContextHolder.clearContext();
            sendUnauthorizedResponse(request, response, "Invalid token");
        }
    }

    /**
     * Send 401 Unauthorized response for invalid tokens
     */
    private void sendUnauthorizedResponse(HttpServletRequest request, HttpServletResponse response, String message) 
            throws IOException {
        SecurityContextHolder.clearContext();
        
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.UNAUTHORIZED.value(),
                HttpStatus.UNAUTHORIZED.getReasonPhrase(),
                message,
                request.getRequestURI(),
                ErrorCode.UNAUTHORIZED.getCode()
        );

        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
    }
}
