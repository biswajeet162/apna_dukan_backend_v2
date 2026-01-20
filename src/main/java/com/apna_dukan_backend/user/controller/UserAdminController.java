package com.apna_dukan_backend.user.controller;

import com.apna_dukan_backend.user.dto.UpdateRoleRequest;
import com.apna_dukan_backend.user.exception.UserNotFoundException;
import com.apna_dukan_backend.user.model.User;
import com.apna_dukan_backend.user.repository.UserRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/admin/users")
@Tag(name = "User Admin", description = "Admin API for managing user roles")
@RequiredArgsConstructor
public class UserAdminController {

    private final UserRepository userRepository;

    @PutMapping("/{userId}/role")
    @PreAuthorize("hasAuthority('ROLE_SUPER_ADMIN_ROLE')")
    @Operation(summary = "Update user role", 
               description = "Update a user's role. Only SUPER_ADMIN_ROLE can perform this operation.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User role updated successfully",
                    content = @Content(schema = @Schema(implementation = User.class))),
            @ApiResponse(responseCode = "404", description = "User not found"),
            @ApiResponse(responseCode = "403", description = "Access denied - SUPER_ADMIN_ROLE required")
    })
    public ResponseEntity<User> updateUserRole(
            @PathVariable UUID userId,
            @Valid @RequestBody UpdateRoleRequest request) {
        
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));

        user.setRole(request.getRole());
        User updatedUser = userRepository.save(user);

        return ResponseEntity.ok(updatedUser);
    }
}

