package com.apna_dukan_backend.user.model.dto.profile;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * DTO for updating user profile.
 * All fields are optional - only provided fields will be updated.
 */
@Data
public class UserProfileUpdateRequestDto {
    @Size(min = 1, max = 200, message = "Name must be between 1 and 200 characters")
    private String name;

    @Email(message = "Email must be valid")
    private String email;

    @Pattern(regexp = "^[0-9]{10,15}$", message = "Phone number must be 10-15 digits")
    private String phone;
}

