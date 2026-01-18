package com.apna_dukan_backend.catalog.product.model.dto;

import java.util.List;
import java.util.UUID;

public class ProductListingResponseDto {
    private UUID productGroupId;
    private String productGroupName;
    private List<ProductListItemDto> products;
    private PaginationMetadata pagination;

    public ProductListingResponseDto() {
    }

    public ProductListingResponseDto(UUID productGroupId, String productGroupName, List<ProductListItemDto> products) {
        this.productGroupId = productGroupId;
        this.productGroupName = productGroupName;
        this.products = products;
    }

    public ProductListingResponseDto(UUID productGroupId, String productGroupName, List<ProductListItemDto> products, PaginationMetadata pagination) {
        this.productGroupId = productGroupId;
        this.productGroupName = productGroupName;
        this.products = products;
        this.pagination = pagination;
    }

    public UUID getProductGroupId() {
        return productGroupId;
    }

    public void setProductGroupId(UUID productGroupId) {
        this.productGroupId = productGroupId;
    }

    public String getProductGroupName() {
        return productGroupName;
    }

    public void setProductGroupName(String productGroupName) {
        this.productGroupName = productGroupName;
    }

    public List<ProductListItemDto> getProducts() {
        return products;
    }

    public void setProducts(List<ProductListItemDto> products) {
        this.products = products;
    }

    public PaginationMetadata getPagination() {
        return pagination;
    }

    public void setPagination(PaginationMetadata pagination) {
        this.pagination = pagination;
    }

    public static class PaginationMetadata {
        private int page;
        private int size;
        private long totalElements;
        private int totalPages;
        private boolean hasNext;
        private boolean hasPrevious;

        public PaginationMetadata() {
        }

        public PaginationMetadata(int page, int size, long totalElements, int totalPages, boolean hasNext, boolean hasPrevious) {
            this.page = page;
            this.size = size;
            this.totalElements = totalElements;
            this.totalPages = totalPages;
            this.hasNext = hasNext;
            this.hasPrevious = hasPrevious;
        }

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public long getTotalElements() {
            return totalElements;
        }

        public void setTotalElements(long totalElements) {
            this.totalElements = totalElements;
        }

        public int getTotalPages() {
            return totalPages;
        }

        public void setTotalPages(int totalPages) {
            this.totalPages = totalPages;
        }

        public boolean isHasNext() {
            return hasNext;
        }

        public void setHasNext(boolean hasNext) {
            this.hasNext = hasNext;
        }

        public boolean isHasPrevious() {
            return hasPrevious;
        }

        public void setHasPrevious(boolean hasPrevious) {
            this.hasPrevious = hasPrevious;
        }
    }
}

