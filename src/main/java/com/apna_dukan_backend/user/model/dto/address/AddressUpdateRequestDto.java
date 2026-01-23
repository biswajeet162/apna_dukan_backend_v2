package com.apna_dukan_backend.user.model.dto.address;

import com.apna_dukan_backend.user.model.AddressType;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * DTO for updating an existing address.
 * All fields are optional - only provided fields will be updated.
 */
@Data
public class AddressUpdateRequestDto {
    @Size(min = 1, max = 100, message = "Name must be between 1 and 100 characters")
    private String name;

    @Pattern(regexp = "^[0-9]{10,15}$", message = "Phone number must be 10-15 digits")
    private String phone;

    @Size(min = 1, max = 200, message = "Address line 1 must be between 1 and 200 characters")
    private String line1;

    @Size(max = 200, message = "Address line 2 must be less than 200 characters")
    private String line2;

    @Size(min = 1, max = 100, message = "City must be between 1 and 100 characters")
    private String city;

    @Size(min = 1, max = 100, message = "State must be between 1 and 100 characters")
    private String state;

    @Pattern(regexp = "^[0-9]{6}$", message = "Pincode must be exactly 6 digits")
    private String pincode;

    @Size(max = 100, message = "Country must be less than 100 characters")
    private String country;

    private AddressType type;

    private Boolean isDefault;
}

