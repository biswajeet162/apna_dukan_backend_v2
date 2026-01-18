package com.apna_dukan_backend.common.exception;

public enum ErrorCode {
    // Resource Not Found (404)
    RESOURCE_NOT_FOUND("RESOURCE_NOT_FOUND", "The requested resource was not found"),
    SECTION_NOT_FOUND("SECTION_NOT_FOUND", "Section not found"),
    CATEGORY_NOT_FOUND("CATEGORY_NOT_FOUND", "Category not found"),
    SUB_CATEGORY_NOT_FOUND("SUB_CATEGORY_NOT_FOUND", "SubCategory not found"),
    PRODUCT_GROUP_NOT_FOUND("PRODUCT_GROUP_NOT_FOUND", "ProductGroup not found"),
    
    // Bad Request (400)
    INVALID_REQUEST("INVALID_REQUEST", "Invalid request data"),
    INVALID_SECTION_CODE("INVALID_SECTION_CODE", "Invalid section code"),
    VALIDATION_ERROR("VALIDATION_ERROR", "Validation failed"),
    INVALID_INPUT("INVALID_INPUT", "Invalid input provided"),
    
    // Conflict (409)
    RESOURCE_CONFLICT("RESOURCE_CONFLICT", "Resource conflict"),
    DUPLICATE_ENTRY("DUPLICATE_ENTRY", "Duplicate entry detected"),
    
    // Internal Server Error (500)
    INTERNAL_SERVER_ERROR("INTERNAL_SERVER_ERROR", "An internal server error occurred"),
    DATABASE_ERROR("DATABASE_ERROR", "Database operation failed"),
    
    // Method Not Allowed (405)
    METHOD_NOT_ALLOWED("METHOD_NOT_ALLOWED", "HTTP method not allowed"),
    
    // Unsupported Media Type (415)
    UNSUPPORTED_MEDIA_TYPE("UNSUPPORTED_MEDIA_TYPE", "Unsupported media type");

    private final String code;
    private final String defaultMessage;

    ErrorCode(String code, String defaultMessage) {
        this.code = code;
        this.defaultMessage = defaultMessage;
    }

    public String getCode() {
        return code;
    }

    public String getDefaultMessage() {
        return defaultMessage;
    }
}

