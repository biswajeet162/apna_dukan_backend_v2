package com.apna_dukan_backend.user.dto;

import com.apna_dukan_backend.user.model.Role;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateRoleRequest {
    @NotNull(message = "Role is required")
    private Role role;
}

