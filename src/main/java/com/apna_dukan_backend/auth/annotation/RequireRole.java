package com.apna_dukan_backend.auth.annotation;

import com.apna_dukan_backend.user.model.Role;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation to specify required roles for accessing a method or endpoint.
 * Use with @PreAuthorize annotation from Spring Security.
 * 
 * Example usage:
 * @PreAuthorize("@requireRole.hasRole('ADMIN_ROLE')")
 * @RequireRole(Role.ADMIN_ROLE)
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface RequireRole {
    Role[] value() default {};
}

