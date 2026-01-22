package com.apna_dukan_backend.user.dto.user;

import com.apna_dukan_backend.user.model.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileResponse {
    private UUID userId;
    private String name;
    private String email;
    private String phone;
    private Boolean emailVerified;
    private Boolean phoneVerified;
    private Role role;
}

