# System User Management Guide

## Overview

The system now supports three roles:
- **USER**: Regular application users
- **ADMIN**: Administrative users who can manage the system
- **SYSTEM**: System-level users who can create admins and manage all users

## Role Hierarchy

```
SYSTEM (Highest)
  ↓
ADMIN
  ↓
USER (Lowest)
```

## Automatic User Creation

On application startup, the system automatically creates:

1. **System User**
   - Email: `system@apnadukan.com`
   - Password: `System@123`
   - Role: `SYSTEM`
   - **⚠️ IMPORTANT**: Change this password in production!

2. **Admin User**
   - Email: `admin@apnadukan.com`
   - Password: `Admin@123`
   - Role: `ADMIN`
   - **⚠️ IMPORTANT**: Change this password in production!

## How to Create System/Admin Users

### Option 1: Using System User API (Recommended)

1. **Login as System User**
   ```
   POST /api/auth/login
   {
     "emailOrPhone": "system@apnadukan.com",
     "password": "System@123"
   }
   ```

2. **Create Admin User**
   ```
   POST /api/system/users/create-admin
   Authorization: Bearer <system_user_token>
   {
     "name": "New Admin",
     "email": "newadmin@example.com",
     "phone": "9876543210",
     "password": "SecurePassword123"
   }
   ```

3. **Create Another System User** (if needed)
   ```
   POST /api/system/users/create-system
   Authorization: Bearer <system_user_token>
   {
     "name": "System Admin 2",
     "email": "system2@example.com",
     "phone": "9876543211",
     "password": "SecurePassword123"
   }
   ```

### Option 2: Using AdminInitializer (Development Only)

The `AdminInitializer` class automatically creates system and admin users on startup. You can modify it in:
- File: `src/main/java/com/apna_dukan_backend/config/AdminInitializer.java`

## Promoting Users to Admin

### Using System User

Only SYSTEM role can promote users to ADMIN:

```
PUT /api/system/users/{userId}/promote-to-admin
Authorization: Bearer <system_user_token>
```

### Using Admin Controller

SYSTEM and ADMIN can update roles, but only SYSTEM can promote to ADMIN:

```
PUT /api/admin/users/{userId}/role
Authorization: Bearer <system_user_token>
{
  "role": "ADMIN"
}
```

**Rules:**
- ✅ SYSTEM can promote USER → ADMIN
- ✅ SYSTEM can demote ADMIN → USER
- ❌ ADMIN cannot promote USER → ADMIN (only SYSTEM can)
- ✅ ADMIN can demote ADMIN → USER
- ❌ No one can change SYSTEM user role

## API Endpoints

### System User Endpoints (Requires SYSTEM role)

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/system/users/create-admin` | Create a new admin user |
| POST | `/api/system/users/create-system` | Create a new system user |
| PUT | `/api/system/users/{userId}/promote-to-admin` | Promote user to admin |
| PUT | `/api/system/users/{userId}/demote-to-user` | Demote admin to user |

### Admin User Endpoints (Requires ADMIN or SYSTEM role)

| Method | Endpoint | Description |
|--------|----------|-------------|
| PUT | `/api/admin/users/{userId}/role` | Update user role (with restrictions) |

## Security Configuration

- `/api/system/**` → Requires `ROLE_SYSTEM`
- `/api/admin/**` → Requires `ROLE_ADMIN` or `ROLE_SYSTEM`
- `/api/user/**` → Requires `ROLE_USER`

## Example Workflow

### Scenario: Create a new admin user

1. **Login as System User**
   ```bash
   curl -X POST http://localhost:8080/api/auth/login \
     -H "Content-Type: application/json" \
     -d '{
       "emailOrPhone": "system@apnadukan.com",
       "password": "System@123"
     }'
   ```

2. **Create Admin User**
   ```bash
   curl -X POST http://localhost:8080/api/system/users/create-admin \
     -H "Content-Type: application/json" \
     -H "Authorization: Bearer <access_token_from_step_1>" \
     -d '{
       "name": "John Admin",
       "email": "john.admin@example.com",
       "phone": "9876543210",
       "password": "SecurePassword123"
     }'
   ```

3. **Verify Admin User**
   ```bash
   curl -X POST http://localhost:8080/api/auth/login \
     -H "Content-Type: application/json" \
     -d '{
       "emailOrPhone": "john.admin@example.com",
       "password": "SecurePassword123"
     }'
   ```

### Scenario: Promote existing user to admin

1. **Login as System User** (get token)

2. **Promote User**
   ```bash
   curl -X PUT http://localhost:8080/api/system/users/{userId}/promote-to-admin \
     -H "Authorization: Bearer <system_user_token>"
   ```

## Important Notes

1. **Change Default Passwords**: The default system and admin passwords should be changed immediately in production.

2. **System User Protection**: SYSTEM user roles cannot be changed by anyone, including other SYSTEM users.

3. **Role Promotion Rules**:
   - Only SYSTEM can promote USER → ADMIN
   - ADMIN can only demote ADMIN → USER
   - SYSTEM can promote/demote freely (except SYSTEM users)

4. **Token Expiration**: 
   - Access tokens: 30 minutes
   - Refresh tokens: 7 days

5. **Database**: System and admin users are created automatically on first startup. If they already exist, they won't be recreated.

## Troubleshooting

### Cannot create admin user
- Ensure you're logged in as SYSTEM user
- Check that the email/phone doesn't already exist
- Verify the token is valid and not expired

### Cannot promote user to admin
- Only SYSTEM role can promote to ADMIN
- Check that you're using a SYSTEM user token
- Verify the target user exists and is not already a SYSTEM user

### System user not created
- Check application logs for errors
- Verify database connection
- Check if user already exists (won't recreate)

