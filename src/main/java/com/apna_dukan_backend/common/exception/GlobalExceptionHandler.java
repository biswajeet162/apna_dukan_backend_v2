package com.apna_dukan_backend.common.exception;

import com.apna_dukan_backend.common.exception.dto.ErrorResponse;
import com.apna_dukan_backend.catalog.category.exception.CategoryNotFoundException;
import com.apna_dukan_backend.catalog.category.exception.InvalidSectionException;
import com.apna_dukan_backend.catalog.layout.exception.SectionNotFoundException;
import com.apna_dukan_backend.catalog.subcategory.exception.SubCategoryNotFoundException;
import com.apna_dukan_backend.catalog.productgroup.exception.ProductGroupNotFoundException;
import com.apna_dukan_backend.catalog.product.exception.DuplicateProductCodeException;
import com.apna_dukan_backend.catalog.product.exception.ProductNotFoundException;
import com.apna_dukan_backend.catalog.variant.exception.DuplicateSkuException;
import com.apna_dukan_backend.catalog.variant.exception.VariantNotFoundException;
import com.apna_dukan_backend.catalog.pricing.exception.PricingNotFoundException;
import com.apna_dukan_backend.catalog.pricing.exception.InvalidPricingException;
import com.apna_dukan_backend.catalog.pricing.exception.DuplicateActivePricingException;
import com.apna_dukan_backend.inventory.exception.InventoryNotFoundException;
import com.apna_dukan_backend.inventory.exception.InvalidInventoryException;
import com.apna_dukan_backend.inventory.exception.DuplicateInventoryException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * Handle resource not found exceptions (404)
     */
    @ExceptionHandler(SectionNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleSectionNotFoundException(
            SectionNotFoundException ex, HttpServletRequest request) {
        logger.warn("Section not found: {}", ex.getMessage());
        
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                ex.getMessage(),
                request.getRequestURI(),
                ErrorCode.SECTION_NOT_FOUND.getCode()
        );
        
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    /**
     * Handle invalid section code exceptions (400)
     */
    @ExceptionHandler(InvalidSectionException.class)
    public ResponseEntity<ErrorResponse> handleInvalidSectionException(
            InvalidSectionException ex, HttpServletRequest request) {
        logger.warn("Invalid section code: {}", ex.getMessage());
        
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                ex.getMessage(),
                request.getRequestURI(),
                ErrorCode.INVALID_SECTION_CODE.getCode()
        );
        
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    /**
     * Handle category not found exceptions (404)
     */
    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCategoryNotFoundException(
            CategoryNotFoundException ex, HttpServletRequest request) {
        logger.warn("Category not found: {}", ex.getMessage());
        
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                ex.getMessage(),
                request.getRequestURI(),
                ErrorCode.CATEGORY_NOT_FOUND.getCode()
        );
        
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    /**
     * Handle subcategory not found exceptions (404)
     */
    @ExceptionHandler(SubCategoryNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleSubCategoryNotFoundException(
            SubCategoryNotFoundException ex, HttpServletRequest request) {
        logger.warn("SubCategory not found: {}", ex.getMessage());
        
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                ex.getMessage(),
                request.getRequestURI(),
                ErrorCode.SUB_CATEGORY_NOT_FOUND.getCode()
        );
        
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    /**
     * Handle product group not found exceptions (404)
     */
    @ExceptionHandler(ProductGroupNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleProductGroupNotFoundException(
            ProductGroupNotFoundException ex, HttpServletRequest request) {
        logger.warn("ProductGroup not found: {}", ex.getMessage());
        
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                ex.getMessage(),
                request.getRequestURI(),
                ErrorCode.PRODUCT_GROUP_NOT_FOUND.getCode()
        );
        
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    /**
     * Handle product not found exceptions (404)
     */
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleProductNotFoundException(
            ProductNotFoundException ex, HttpServletRequest request) {
        logger.warn("Product not found: {}", ex.getMessage());
        
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                ex.getMessage(),
                request.getRequestURI(),
                ErrorCode.PRODUCT_NOT_FOUND.getCode()
        );
        
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    /**
     * Handle duplicate product code exceptions (409)
     */
    @ExceptionHandler(DuplicateProductCodeException.class)
    public ResponseEntity<ErrorResponse> handleDuplicateProductCodeException(
            DuplicateProductCodeException ex, HttpServletRequest request) {
        logger.warn("Duplicate product code: {}", ex.getMessage());
        
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.CONFLICT.value(),
                HttpStatus.CONFLICT.getReasonPhrase(),
                ex.getMessage(),
                request.getRequestURI(),
                ErrorCode.DUPLICATE_ENTRY.getCode()
        );
        
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }

    /**
     * Handle variant not found exceptions (404)
     */
    @ExceptionHandler(VariantNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleVariantNotFoundException(
            VariantNotFoundException ex, HttpServletRequest request) {
        logger.warn("Variant not found: {}", ex.getMessage());
        
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                ex.getMessage(),
                request.getRequestURI(),
                ErrorCode.VARIANT_NOT_FOUND.getCode()
        );
        
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    /**
     * Handle duplicate SKU exceptions (409)
     */
    @ExceptionHandler(DuplicateSkuException.class)
    public ResponseEntity<ErrorResponse> handleDuplicateSkuException(
            DuplicateSkuException ex, HttpServletRequest request) {
        logger.warn("Duplicate SKU: {}", ex.getMessage());
        
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.CONFLICT.value(),
                HttpStatus.CONFLICT.getReasonPhrase(),
                ex.getMessage(),
                request.getRequestURI(),
                ErrorCode.DUPLICATE_SKU.getCode()
        );
        
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }

    /**
     * Handle pricing not found exceptions (404)
     */
    @ExceptionHandler(PricingNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlePricingNotFoundException(
            PricingNotFoundException ex, HttpServletRequest request) {
        logger.warn("Pricing not found: {}", ex.getMessage());
        
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                ex.getMessage(),
                request.getRequestURI(),
                ErrorCode.PRICING_NOT_FOUND.getCode()
        );
        
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    /**
     * Handle invalid pricing exceptions (400)
     */
    @ExceptionHandler(InvalidPricingException.class)
    public ResponseEntity<ErrorResponse> handleInvalidPricingException(
            InvalidPricingException ex, HttpServletRequest request) {
        logger.warn("Invalid pricing: {}", ex.getMessage());
        
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                ex.getMessage(),
                request.getRequestURI(),
                ErrorCode.INVALID_PRICING.getCode()
        );
        
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    /**
     * Handle duplicate active pricing exceptions (409)
     */
    @ExceptionHandler(DuplicateActivePricingException.class)
    public ResponseEntity<ErrorResponse> handleDuplicateActivePricingException(
            DuplicateActivePricingException ex, HttpServletRequest request) {
        logger.warn("Duplicate active pricing: {}", ex.getMessage());
        
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.CONFLICT.value(),
                HttpStatus.CONFLICT.getReasonPhrase(),
                ex.getMessage(),
                request.getRequestURI(),
                ErrorCode.DUPLICATE_ACTIVE_PRICING.getCode()
        );
        
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }

    /**
     * Handle inventory not found exceptions (404)
     */
    @ExceptionHandler(InventoryNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleInventoryNotFoundException(
            InventoryNotFoundException ex, HttpServletRequest request) {
        logger.warn("Inventory not found: {}", ex.getMessage());
        
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                ex.getMessage(),
                request.getRequestURI(),
                ErrorCode.INVENTORY_NOT_FOUND.getCode()
        );
        
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    /**
     * Handle invalid inventory exceptions (400)
     */
    @ExceptionHandler(InvalidInventoryException.class)
    public ResponseEntity<ErrorResponse> handleInvalidInventoryException(
            InvalidInventoryException ex, HttpServletRequest request) {
        logger.warn("Invalid inventory: {}", ex.getMessage());
        
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                ex.getMessage(),
                request.getRequestURI(),
                ErrorCode.INVALID_INVENTORY.getCode()
        );
        
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    /**
     * Handle duplicate inventory exceptions (409)
     */
    @ExceptionHandler(DuplicateInventoryException.class)
    public ResponseEntity<ErrorResponse> handleDuplicateInventoryException(
            DuplicateInventoryException ex, HttpServletRequest request) {
        logger.warn("Duplicate inventory: {}", ex.getMessage());
        
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.CONFLICT.value(),
                HttpStatus.CONFLICT.getReasonPhrase(),
                ex.getMessage(),
                request.getRequestURI(),
                ErrorCode.DUPLICATE_INVENTORY.getCode()
        );
        
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }

    /**
     * Handle validation errors from @Valid annotations (400)
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(
            MethodArgumentNotValidException ex, HttpServletRequest request) {
        logger.warn("Validation failed: {}", ex.getMessage());
        
        List<ErrorResponse.ValidationError> validationErrors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> new ErrorResponse.ValidationError(
                        error.getField(),
                        error.getRejectedValue(),
                        error.getDefaultMessage()
                ))
                .collect(Collectors.toList());

        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                "Validation failed for one or more fields",
                request.getRequestURI(),
                ErrorCode.VALIDATION_ERROR.getCode()
        );
        errorResponse.setValidationErrors(validationErrors);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    /**
     * Handle constraint violation exceptions (400)
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolationException(
            ConstraintViolationException ex, HttpServletRequest request) {
        logger.warn("Constraint violation: {}", ex.getMessage());
        
        List<ErrorResponse.ValidationError> validationErrors = ex.getConstraintViolations()
                .stream()
                .map(violation -> {
                    String field = violation.getPropertyPath().toString();
                    return new ErrorResponse.ValidationError(
                            field,
                            violation.getInvalidValue(),
                            violation.getMessage()
                    );
                })
                .collect(Collectors.toList());

        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                "Constraint violation occurred",
                request.getRequestURI(),
                ErrorCode.VALIDATION_ERROR.getCode()
        );
        errorResponse.setValidationErrors(validationErrors);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    /**
     * Handle missing request parameters (400)
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ErrorResponse> handleMissingServletRequestParameterException(
            MissingServletRequestParameterException ex, HttpServletRequest request) {
        logger.warn("Missing request parameter: {}", ex.getParameterName());
        
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                String.format("Required parameter '%s' is missing", ex.getParameterName()),
                request.getRequestURI(),
                ErrorCode.INVALID_REQUEST.getCode()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    /**
     * Handle method argument type mismatch (400)
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentTypeMismatchException(
            MethodArgumentTypeMismatchException ex, HttpServletRequest request) {
        logger.warn("Method argument type mismatch: {}", ex.getName());
        
        String message = String.format("Parameter '%s' should be of type %s",
                ex.getName(),
                ex.getRequiredType() != null ? ex.getRequiredType().getSimpleName() : "unknown");

        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                message,
                request.getRequestURI(),
                ErrorCode.INVALID_INPUT.getCode()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    /**
     * Handle malformed JSON requests (400)
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleHttpMessageNotReadableException(
            HttpMessageNotReadableException ex, HttpServletRequest request) {
        logger.warn("Malformed JSON request: {}", ex.getMessage());
        
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                "Malformed JSON request. Please check your request body format.",
                request.getRequestURI(),
                ErrorCode.INVALID_REQUEST.getCode()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    /**
     * Handle HTTP method not supported (405)
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorResponse> handleHttpRequestMethodNotSupportedException(
            HttpRequestMethodNotSupportedException ex, HttpServletRequest request) {
        logger.warn("HTTP method not supported: {} for path {}", ex.getMethod(), request.getRequestURI());
        
        String supportedMethods = ex.getSupportedMethods() != null 
                ? String.join(", ", ex.getSupportedMethods())
                : "N/A";

        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.METHOD_NOT_ALLOWED.value(),
                HttpStatus.METHOD_NOT_ALLOWED.getReasonPhrase(),
                String.format("Method '%s' is not supported. Supported methods: %s", 
                        ex.getMethod(), supportedMethods),
                request.getRequestURI(),
                ErrorCode.METHOD_NOT_ALLOWED.getCode()
        );

        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(errorResponse);
    }

    /**
     * Handle no handler found (404)
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ErrorResponse> handleNoHandlerFoundException(
            NoHandlerFoundException ex, HttpServletRequest request) {
        logger.warn("No handler found for: {} {}", ex.getHttpMethod(), ex.getRequestURL());
        
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                String.format("No handler found for %s %s", ex.getHttpMethod(), ex.getRequestURL()),
                request.getRequestURI(),
                ErrorCode.RESOURCE_NOT_FOUND.getCode()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    /**
     * Handle all other exceptions (500)
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(
            Exception ex, HttpServletRequest request) {
        logger.error("Unexpected error occurred: {}", ex.getMessage(), ex);
        
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                "An unexpected error occurred. Please contact support if the problem persists.",
                request.getRequestURI(),
                ErrorCode.INTERNAL_SERVER_ERROR.getCode()
        );

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }
}
