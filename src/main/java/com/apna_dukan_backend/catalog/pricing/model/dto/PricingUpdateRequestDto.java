package com.apna_dukan_backend.catalog.pricing.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PricingUpdateRequestDto {
    @DecimalMin(value = "0.01", message = "MRP must be greater than 0")
    @Schema(description = "Maximum Retail Price", example = "1000.00")
    private BigDecimal mrp;

    @DecimalMin(value = "0.01", message = "Selling price must be greater than 0")
    @Schema(description = "Selling price", example = "800.00")
    private BigDecimal sellingPrice;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @Schema(description = "Valid from date and time", example = "2024-01-01T00:00:00")
    private LocalDateTime validFrom;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @Schema(description = "Valid to date and time", example = "2024-12-31T23:59:59")
    private LocalDateTime validTo;

    @Schema(description = "Active status", example = "true")
    private Boolean active;

    public PricingUpdateRequestDto() {
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

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}


