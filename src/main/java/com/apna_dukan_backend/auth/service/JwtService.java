package com.apna_dukan_backend.auth.service;

import com.apna_dukan_backend.user.model.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;

@Service
public class JwtService {
    @Value("${jwt.secret:your-secret-key-should-be-at-least-256-bits-long-for-hs256-algorithm}")
    private String secret;

    @Value("${jwt.expiration:1800000}") // 30 minutes in milliseconds
    private Long expiration;

    @Value("${jwt.refresh-expiration:604800000}") // 7 days in milliseconds
    private Long refreshExpiration;

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    public String extractUserId(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Role extractRole(String token) {
        String roleStr = extractClaim(token, claims -> claims.get("role", String.class));
        return roleStr != null ? Role.valueOf(roleStr) : null;
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(UUID userId, Role role) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", role.name());
        return createToken(claims, userId.toString(), expiration);
    }

    public String generateRefreshToken(UUID userId) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userId.toString(), refreshExpiration);
    }

    private String createToken(Map<String, Object> claims, String subject, Long expirationTime) {
        return Jwts.builder()
                .claims(claims)
                .subject(subject)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(getSigningKey())
                .compact();
    }

    public Boolean validateToken(String token) {
        try {
            return !isTokenExpired(token);
        } catch (Exception e) {
            return false;
        }
    }

    // Legacy method for backward compatibility during migration
    public String extractUsername(String token) {
        return extractUserId(token);
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String userId = extractUserId(token);
        // For UserDetails, we'll need to adapt - this is for backward compatibility
        return (userId != null && !isTokenExpired(token));
    }
}

