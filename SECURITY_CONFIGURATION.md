# Security Configuration Guide

## Overview

The security system has been hardened to support:
1. **Public APIs** with optional JWT (anonymous browsing + authenticated context)
2. **Admin APIs** with strict JWT requirement (ROLE_ADMIN)
3. **User APIs** with JWT requirement (ROLE_USER)
4. **System APIs** with JWT requirement (ROLE_SYSTEM)

## Key Security Principles

### 1. Missing Token = Anonymous
- If `Authorization` header is **absent**, request proceeds as anonymous
- Public APIs work without authentication
- No error is thrown

### 2. Present Token = MUST Validate
- If `Authorization` header is **present**, JWT MUST be valid
- Invalid token → **401 Unauthorized** (even for public APIs)
- Valid token → SecurityContext is set with user details

### 3. Role-Based Access Control
- Admin APIs require `ROLE_ADMIN` or `ROLE_SYSTEM`
- User APIs require `ROLE_USER`
- System APIs require `ROLE_SYSTEM`
- Wrong role → **403 Forbidden**

## API Access Classification

### Public APIs (JWT Optional)
These endpoints work without authentication, but can use JWT context if token is present:

```
GET /api/v1/layout/**
GET /api/v1/section/**
GET /api/v1/category/**
GET /api/v1/subCategory/**
GET /api/v1/productGroup/**
GET /api/v1/product/**
GET /api/v1/products/**
GET /api/variants/**
```

**Behavior:**
- ✅ No token → Request proceeds as anonymous
- ✅ Valid token → User context is set, personalized responses possible
- ❌ Invalid token → 401 Unauthorized

### User APIs (JWT Required - ROLE_USER)
These endpoints require authentication with USER role:

```
/api/user/**
/api/cart/**
/api/order/**
/api/review/**
```

**Behavior:**
- ❌ No token → 401 Unauthorized
- ❌ Invalid token → 401 Unauthorized
- ❌ Valid token but wrong role → 403 Forbidden
- ✅ Valid token with ROLE_USER → Access granted

### Admin APIs (JWT Required - ROLE_ADMIN)
These endpoints require authentication with ADMIN or SYSTEM role:

```
/api/admin/**
/api/v1/admin/**
```

**Behavior:**
- ❌ No token → 401 Unauthorized
- ❌ Invalid token → 401 Unauthorized
- ❌ Valid token but role != ADMIN/SYSTEM → 403 Forbidden
- ✅ Valid token with ROLE_ADMIN or ROLE_SYSTEM → Access granted

### System APIs (JWT Required - ROLE_SYSTEM)
These endpoints require authentication with SYSTEM role:

```
/api/system/**
```

**Behavior:**
- ❌ No token → 401 Unauthorized
- ❌ Invalid token → 401 Unauthorized
- ❌ Valid token but role != SYSTEM → 403 Forbidden
- ✅ Valid token with ROLE_SYSTEM → Access granted

## JWT Token Structure

JWT payload must include:
```json
{
  "sub": "userId (UUID)",
  "role": "USER | ADMIN | SYSTEM",
  "iat": 1234567890,
  "exp": 1234567890
}
```

## Error Responses

### 401 Unauthorized
Returned when:
- Token is missing on protected endpoint
- Token is invalid or expired
- Token is present but malformed

**Response:**
```json
{
  "status": 401,
  "error": "Unauthorized",
  "message": "Invalid or expired token",
  "path": "/api/admin/users",
  "code": "UNAUTHORIZED"
}
```

### 403 Forbidden
Returned when:
- Token is valid but user lacks required role
- User tries to access admin endpoint with USER role

**Response:**
```json
{
  "status": 403,
  "error": "Forbidden",
  "message": "Access denied. You do not have permission to access this resource.",
  "path": "/api/admin/users",
  "code": "FORBIDDEN"
}
```

## Client Implementation Guide

### Guest User (No Authentication)
```dart
// Flutter example
final response = await http.get(
  Uri.parse('https://api.example.com/api/v1/product/123'),
  // No Authorization header
);
```

### Authenticated User
```dart
// Flutter example
final response = await http.get(
  Uri.parse('https://api.example.com/api/v1/product/123'),
  headers: {
    'Authorization': 'Bearer $accessToken',
  },
);
```

**Important:** Always send the Authorization header when user is logged in, even for public APIs. This enables:
- Personalized responses
- Recently viewed products
- Recommendations
- Analytics

## Security Flow Diagram

```
Request → JwtAuthenticationFilter
    │
    ├─ No Authorization header?
    │   └─ YES → Continue as anonymous (public APIs work)
    │
    └─ Authorization header present?
        ├─ Extract JWT
        ├─ Validate JWT
        │   ├─ Invalid → 401 Unauthorized
        │   └─ Valid → Load user, set SecurityContext
        │
        └─ Continue to SecurityConfig
            ├─ Public API (permitAll) → Allow
            ├─ User API → Check ROLE_USER
            ├─ Admin API → Check ROLE_ADMIN/SYSTEM
            └─ System API → Check ROLE_SYSTEM
```

## Testing Scenarios

### Scenario 1: Public API without token
```bash
curl -X GET http://localhost:8080/api/v1/product/123
# ✅ 200 OK (anonymous)
```

### Scenario 2: Public API with valid token
```bash
curl -X GET http://localhost:8080/api/v1/product/123 \
  -H "Authorization: Bearer <valid_token>"
# ✅ 200 OK (authenticated, user context available)
```

### Scenario 3: Public API with invalid token
```bash
curl -X GET http://localhost:8080/api/v1/product/123 \
  -H "Authorization: Bearer invalid_token"
# ❌ 401 Unauthorized
```

### Scenario 4: Admin API without token
```bash
curl -X GET http://localhost:8080/api/admin/users
# ❌ 401 Unauthorized
```

### Scenario 5: Admin API with USER role token
```bash
curl -X GET http://localhost:8080/api/admin/users \
  -H "Authorization: Bearer <user_token>"
# ❌ 403 Forbidden
```

### Scenario 6: Admin API with ADMIN role token
```bash
curl -X GET http://localhost:8080/api/admin/users \
  -H "Authorization: Bearer <admin_token>"
# ✅ 200 OK
```

## Security Best Practices

1. **Always validate tokens when present**
   - Never ignore invalid tokens
   - Always return 401 for invalid tokens

2. **Use role-based access control**
   - Don't check roles in controllers
   - Use Spring Security annotations

3. **Stateless authentication**
   - No server-side sessions
   - All state in JWT token

4. **Token expiration**
   - Access tokens: 30 minutes
   - Refresh tokens: 7 days

5. **No duplicate endpoints**
   - Same endpoint works for both guest and authenticated users
   - Use SecurityContext to personalize responses

## Controller Examples

### Public API Controller
```java
@RestController
@RequestMapping("/api/v1/product")
public class ProductController {
    
    @GetMapping("/{productId}")
    public ProductResponse getProduct(
            @PathVariable UUID productId,
            @AuthenticationPrincipal UserPrincipal userPrincipal) {
        
        // userPrincipal may be null for anonymous users
        boolean isAuthenticated = userPrincipal != null;
        UUID userId = isAuthenticated ? userPrincipal.getUserId() : null;
        
        // Return personalized response if authenticated
        return productService.getProduct(productId, userId);
    }
}
```

### Admin API Controller
```java
@RestController
@RequestMapping("/api/admin/users")
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class UserAdminController {
    
    @GetMapping
    public List<UserResponse> getUsers(
            @AuthenticationPrincipal UserPrincipal userPrincipal) {
        
        // userPrincipal is never null (enforced by Spring Security)
        return userService.getAllUsers();
    }
}
```

## Migration Notes

- Existing auth endpoints remain unchanged
- No breaking changes to public APIs
- Admin endpoints now strictly require ROLE_ADMIN
- JWT filter runs for all requests but only validates when token is present

