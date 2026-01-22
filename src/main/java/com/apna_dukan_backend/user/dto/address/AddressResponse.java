package com.apna_dukan_backend.user.dto.address;

import com.apna_dukan_backend.user.model.AddressType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressResponse {
    private UUID addressId;
    private String name;
    private String phone;
    private String line1;
    private String line2;
    private String city;
    private String state;
    private String pincode;
    private String country;
    private AddressType type;
    private Boolean isDefault;
    private LocalDateTime createdAt;
}

