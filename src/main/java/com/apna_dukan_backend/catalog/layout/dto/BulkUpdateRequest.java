package com.apna_dukan_backend.catalog.layout.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public class BulkUpdateRequest {
    @NotEmpty
    @Valid
    private List<BulkUpdateItem> sections;

    public BulkUpdateRequest() {
    }

    public List<BulkUpdateItem> getSections() {
        return sections;
    }

    public void setSections(List<BulkUpdateItem> sections) {
        this.sections = sections;
    }
}

