package com.apna_dukan_backend.auth.controller;

import com.apna_dukan_backend.auth.dto.AuthResponse;
import com.apna_dukan_backend.auth.dto.LoginRequest;
import com.apna_dukan_backend.auth.dto.SignupRequest;
import com.apna_dukan_backend.auth.service.AuthService;
import com.apna_dukan_backend.user.dto.auth.RefreshTokenRequest;
import com.apna_dukan_backend.user.dto.auth.RefreshTokenResponse;
import com.apna_dukan_backend.user.security.UserPrincipal;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Authentication", description = "API for user authentication and registration")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    @Operation(summary = "User registration", description = "Register a new user with name, email/phone, and password")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User successfully registered",
                    content = @Content(schema = @Schema(implementation = AuthResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input or user already exists")
    })
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody SignupRequest request) {
        AuthResponse response = authService.signup(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/login")
    @Operation(summary = "User login", description = "Authenticate user with email or phone number and password")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User successfully authenticated",
                    content = @Content(schema = @Schema(implementation = AuthResponse.class))),
            @ApiResponse(responseCode = "401", description = "Invalid credentials")
    })
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
        AuthResponse response = authService.login(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/token/refresh")
    @Operation(summary = "Refresh access token", description = "Get a new access token using refresh token")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Token successfully refreshed",
                    content = @Content(schema = @Schema(implementation = RefreshTokenResponse.class))),
            @ApiResponse(responseCode = "401", description = "Invalid or expired refresh token")
    })
    public ResponseEntity<RefreshTokenResponse> refreshToken(
            @Valid @RequestBody RefreshTokenRequest request) {
        RefreshTokenResponse response = authService.refreshToken(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/logout")
    @Operation(summary = "User logout", description = "Logout user and revoke refresh tokens")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User successfully logged out"),
            @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    public ResponseEntity<Void> logout(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        authService.logout(userPrincipal.getUserId());
        return ResponseEntity.ok().build();
    }
}

