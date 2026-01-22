package com.apna_dukan_backend.user.controller;

import com.apna_dukan_backend.user.dto.user.UpdateProfileRequest;
import com.apna_dukan_backend.user.dto.user.UserProfileResponse;
import com.apna_dukan_backend.user.security.UserPrincipal;
import com.apna_dukan_backend.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/user")
@Tag(name = "User Profile", description = "API for user profile management")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class UserProfileController {
    private final UserService userService;

    @GetMapping("/profile")
    @Operation(summary = "Get user profile", description = "Get the authenticated user's profile")
    public ResponseEntity<UserProfileResponse> getProfile(
            @AuthenticationPrincipal UserPrincipal userPrincipal) {
        UserProfileResponse response = userService.getProfile(userPrincipal.getUserId());
        return ResponseEntity.ok(response);
    }

    @PutMapping("/profile")
    @Operation(summary = "Update user profile", description = "Update the authenticated user's profile")
    public ResponseEntity<UserProfileResponse> updateProfile(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @Valid @RequestBody UpdateProfileRequest request) {
        UserProfileResponse response = userService.updateProfile(userPrincipal.getUserId(), request);
        return ResponseEntity.ok(response);
    }
}

