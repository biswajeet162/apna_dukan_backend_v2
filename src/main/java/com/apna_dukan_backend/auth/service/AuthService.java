package com.apna_dukan_backend.auth.service;

import com.apna_dukan_backend.auth.dto.AuthResponse;
import com.apna_dukan_backend.auth.dto.LoginRequest;
import com.apna_dukan_backend.auth.dto.SignupRequest;
import com.apna_dukan_backend.auth.exception.InvalidCredentialsException;
import com.apna_dukan_backend.auth.exception.UserAlreadyExistsException;
import com.apna_dukan_backend.user.dto.auth.RefreshTokenRequest;
import com.apna_dukan_backend.user.dto.auth.RefreshTokenResponse;
import com.apna_dukan_backend.user.model.AccountStatus;
import com.apna_dukan_backend.user.model.RefreshToken;
import com.apna_dukan_backend.user.model.Role;
import com.apna_dukan_backend.user.model.User;
import com.apna_dukan_backend.user.repository.RefreshTokenRepository;
import com.apna_dukan_backend.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Value("${jwt.refresh-expiration:604800000}") // 7 days
    private Long refreshExpiration;

    @Transactional
    public AuthResponse signup(SignupRequest request) {
        // Validate that at least email or phone is provided
        if ((request.getEmail() == null || request.getEmail().isBlank()) &&
            (request.getPhone() == null || request.getPhone().isBlank())) {
            throw new IllegalArgumentException("Either email or phone must be provided");
        }

        // Check if email already exists
        if (request.getEmail() != null && !request.getEmail().isBlank() && 
            userRepository.existsByEmail(request.getEmail())) {
            throw new UserAlreadyExistsException("Email already exists");
        }

        // Check if phone already exists
        if (request.getPhone() != null && !request.getPhone().isBlank() && 
            userRepository.existsByPhone(request.getPhone())) {
            throw new UserAlreadyExistsException("Phone number already exists");
        }

        // Create new user with default role USER
        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .passwordHash(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .emailVerified(false)
                .phoneVerified(false)
                .status(AccountStatus.ACTIVE)
                .build();

        user = userRepository.save(user);

        // Generate tokens
        String accessToken = jwtService.generateToken(user.getUserId(), user.getRole());
        String refreshToken = createRefreshToken(user.getUserId());

        return AuthResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .userId(user.getUserId())
                .name(user.getName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .role(user.getRole())
                .build();
    }

    @Transactional
    public AuthResponse login(LoginRequest request) {
        // Find user by email or phone
        User user = userRepository.findByEmailOrPhone(request.getEmailOrPhone(), request.getEmailOrPhone())
                .orElseThrow(() -> new InvalidCredentialsException("Invalid email/phone or password"));

        // Authenticate using Spring Security
        String username = user.getEmail() != null ? user.getEmail() : user.getPhone();
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        username,
                        request.getPassword()
                )
        );

        // Generate tokens
        String accessToken = jwtService.generateToken(user.getUserId(), user.getRole());
        String refreshToken = createRefreshToken(user.getUserId());

        return AuthResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .userId(user.getUserId())
                .name(user.getName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .role(user.getRole())
                .build();
    }

    @Transactional
    public RefreshTokenResponse refreshToken(RefreshTokenRequest request) {
        RefreshToken refreshToken = refreshTokenRepository.findByToken(request.getRefreshToken())
                .orElseThrow(() -> new InvalidCredentialsException("Invalid refresh token"));

        if (!refreshToken.isValid()) {
            throw new InvalidCredentialsException("Refresh token is expired or revoked");
        }

        User user = userRepository.findByUserId(refreshToken.getUserId())
                .orElseThrow(() -> new InvalidCredentialsException("User not found"));

        // Generate new tokens
        String newAccessToken = jwtService.generateToken(user.getUserId(), user.getRole());
        String newRefreshToken = createRefreshToken(user.getUserId());

        // Revoke old refresh token
        refreshToken.setRevoked(true);
        refreshTokenRepository.save(refreshToken);

        return RefreshTokenResponse.builder()
                .accessToken(newAccessToken)
                .refreshToken(newRefreshToken)
                .build();
    }

    @Transactional
    public void logout(UUID userId) {
        // Revoke all refresh tokens for the user
        refreshTokenRepository.deleteByUserId(userId);
    }

    private String createRefreshToken(UUID userId) {
        // Delete existing refresh tokens for this user
        refreshTokenRepository.deleteByUserId(userId);

        // Create new refresh token
        String token = jwtService.generateRefreshToken(userId);
        RefreshToken refreshToken = RefreshToken.builder()
                .userId(userId)
                .token(token)
                .expiresAt(LocalDateTime.now().plusSeconds(refreshExpiration / 1000))
                .revoked(false)
                .build();

        refreshTokenRepository.save(refreshToken);
        return token;
    }
}

