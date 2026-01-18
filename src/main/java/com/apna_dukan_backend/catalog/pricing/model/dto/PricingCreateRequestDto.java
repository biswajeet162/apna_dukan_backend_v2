package com.apna_dukan_backend.catalog.pricing.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PricingCreateRequestDto {
    @NotBlank(message = "Currency is required")
    @Schema(description = "Currency code (e.g., INR, USD)", example = "INR", required = true)
    private String currency;

    @NotNull(message = "MRP is required")
    @DecimalMin(value = "0.01", message = "MRP must be greater than 0")
    @Schema(description = "Maximum Retail Price", example = "1000.00", required = true)
    private BigDecimal mrp;

    @NotNull(message = "Selling price is required")
    @DecimalMin(value = "0.01", message = "Selling price must be greater than 0")
    @Schema(description = "Selling price", example = "800.00", required = true)
    private BigDecimal sellingPrice;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @Schema(description = "Valid from date and time (optional)", example = "2024-01-01T00:00:00")
    private LocalDateTime validFrom;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @Schema(description = "Valid to date and time (optional)", example = "2024-12-31T23:59:59")
    private LocalDateTime validTo;

    public PricingCreateRequestDto() {
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public BigDecimal getMrp() {
        return mrp;
    }

    public void setMrp(BigDecimal mrp) {
        this.mrp = mrp;
    }

    public BigDecimal getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(BigDecimal sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public LocalDateTime getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(LocalDateTime validFrom) {
        this.validFrom = validFrom;
    }

    public LocalDateTime getValidTo() {
        return validTo;
    }

    public void setValidTo(LocalDateTime validTo) {
        this.validTo = validTo;
    }
}


