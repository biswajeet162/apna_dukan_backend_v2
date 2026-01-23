package com.apna_dukan_backend.user.model.dto.address;

import com.apna_dukan_backend.user.model.AddressType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * DTO for creating a new address.
 */
@Data
public class AddressCreateRequestDto {
    @NotBlank(message = "Name is required")
    @Size(min = 1, max = 100, message = "Name must be between 1 and 100 characters")
    private String name;

    @NotBlank(message = "Phone is required")
    @Pattern(regexp = "^[0-9]{10,15}$", message = "Phone number must be 10-15 digits")
    private String phone;

    @NotBlank(message = "Address line 1 is required")
    @Size(min = 1, max = 200, message = "Address line 1 must be between 1 and 200 characters")
    private String line1;

    @Size(max = 200, message = "Address line 2 must be less than 200 characters")
    private String line2;

    @NotBlank(message = "City is required")
    @Size(min = 1, max = 100, message = "City must be between 1 and 100 characters")
    private String city;

    @NotBlank(message = "State is required")
    @Size(min = 1, max = 100, message = "State must be between 1 and 100 characters")
    private String state;

    @NotBlank(message = "Pincode is required")
    @Pattern(regexp = "^[0-9]{6}$", message = "Pincode must be exactly 6 digits")
    private String pincode;

    @Size(max = 100, message = "Country must be less than 100 characters")
    private String country;

    @NotNull(message = "Address type is required")
    private AddressType type;

    private Boolean isDefault = false;
}

