package com.apna_dukan_backend.catalog.productgroup.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public class BulkUpdateProductGroupRequest {
    @NotEmpty(message = "ProductGroups list cannot be empty")
    @Valid
    private List<BulkUpdateProductGroupItem> productGroups;

    public BulkUpdateProductGroupRequest() {
    }

    public List<BulkUpdateProductGroupItem> getProductGroups() {
        return productGroups;
    }

    public void setProductGroups(List<BulkUpdateProductGroupItem> productGroups) {
        this.productGroups = productGroups;
    }
}





