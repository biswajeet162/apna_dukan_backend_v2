# API Documentation for Apna Dukan Backend

## Base URL Structure
- **User APIs**: `/api/v1/` or `/api/user/`
- **Admin APIs**: `/api/admin/` or `/api/v1/admin/`
- **All IDs are UUIDs** (String format in Flutter)

---

## 1. USER-FACING APIs (Customer App)

### 1.1 Catalog Layout & Navigation

#### Get Enabled Catalog Sections
- **Endpoint**: `GET /api/user/catalog/layout`
- **Description**: Returns only enabled catalog sections sorted by display order for UI rendering
- **Response**: List of `CatalogSectionDto` with:
  - `sectionId`, `sectionCode`, `name`, `description`, `displayOrder`, `enabled`
- **Use Case**: Homepage navigation, category menu

---

### 1.2 Categories

#### Get Enabled Categories for a Section
- **Endpoint**: `GET /api/v1/section/{sectionId}/categories`
- **Description**: Returns only enabled categories for a section (sectionCode = PRODUCT_CATEGORY), sorted by displayOrder
- **Path Parameter**: `sectionId` (UUID)
- **Response**: `CategorySectionResponseDto` containing:
  - `sectionId`, `sectionCode`
  - `categories[]`: `categoryId`, `sectionId`, `name`, `description`, `code`, `displayOrder`, `enabled`
- **Use Case**: Category listing page, category selection

---

### 1.3 Subcategories

#### Get Enabled Subcategories for a Category
- **Endpoint**: `GET /api/v1/category/{categoryId}/subCategories`
- **Description**: Returns only enabled subcategories for a category, sorted by displayOrder
- **Path Parameter**: `categoryId` (UUID)
- **Response**: `SubCategoryResponseDto` containing:
  - `categoryId`, `categoryName`
  - `subCategories[]`: `subCategoryId`, `categoryId`, `name`, `description`, `code`, `displayOrder`, `enabled`, `imageUrl[]`
- **Use Case**: Subcategory listing, category detail page

---

### 1.4 Product Groups

#### Get Enabled Product Groups for a Subcategory
- **Endpoint**: `GET /api/v1/subCategory/{subCategoryId}/productGroups` (if exists)
- **Note**: Check if this user endpoint exists, or use admin endpoint with filtering

---

### 1.5 Product Listing

#### Get Product Listing for a Product Group
- **Endpoint**: `GET /api/v1/productGroup/{productGroupId}/products`
- **Description**: Returns paginated list of enabled products with default variants, pricing, and availability. Products are sorted by displayOrder. Only products with enabled default variants and active pricing are included.
- **Path Parameter**: `productGroupId` (UUID)
- **Query Parameters**:
  - `page` (int, default: 0, 0-based)
  - `size` (int, default: 20, max: 100)
- **Response**: `ProductListingResponseDto` containing:
  - `productGroupId`, `productGroupName`
  - `products[]`: Each product has:
    - `productId`, `name`, `brand`
    - `image`: `{ primary: string }`
    - `defaultVariant`: `{ variantId, label }`
    - `pricing`: `{ sellingPrice, mrp, discountPercent, currency }`
    - `availability`: `{ inStock: boolean }`
  - `pagination`: `{ page, size, totalElements, totalPages, hasNext, hasPrevious }`
- **Use Case**: Product listing page, product grid

---

### 1.6 Product Details (PDP)

#### Get Product Details
- **Endpoint**: `GET /api/v1/product/{productId}`
- **Description**: Returns complete product details including all enabled variants with pricing and availability. Only enabled products and enabled variants with active pricing are included. Variants without pricing are skipped.
- **Path Parameter**: `productId` (UUID)
- **Response**: `ProductDetailsDto` containing:
  - `productId`, `name`, `brand`, `description`
  - `images`: `{ primary: string, gallery: string[] }`
  - `variants[]`: Each variant has:
    - `variantId`, `label`, `attributes: {}`
    - `pricing`: `{ sellingPrice, mrp, discountPercent, currency }`
    - `availability`: `{ inStock: boolean, availableQuantity: number }`
- **Use Case**: Product detail page, variant selection

---

## 2. ADMIN APIs (Admin Panel)

### 2.1 Catalog Layout Management

#### Get All Catalog Sections (Admin)
- **Endpoint**: `GET /api/admin/catalog/layout`
- **Description**: Returns all catalog sections (enabled and disabled) sorted by display order
- **Response**: List of `CatalogSectionDto` with timestamps

#### Get Catalog Section by ID
- **Endpoint**: `GET /api/admin/catalog/layout/{sectionId}`
- **Path Parameter**: `sectionId` (UUID)
- **Response**: `CatalogSectionDto`

#### Create Catalog Section
- **Endpoint**: `POST /api/admin/catalog/layout`
- **Body**: `CreateSectionRequest` with:
  - `sectionCode` (string, required)
  - `name` (string, required)
  - `description` (string, optional)
  - `displayOrder` (int, required)
- **Response**: `CatalogSectionDto` (201 Created)

#### Update Catalog Section
- **Endpoint**: `PUT /api/admin/catalog/layout/{sectionId}`
- **Path Parameter**: `sectionId` (UUID)
- **Body**: `UpdateSectionRequest` (partial update)
- **Response**: `CatalogSectionDto`

#### Delete Catalog Section
- **Endpoint**: `DELETE /api/admin/catalog/layout/{sectionId}`
- **Path Parameter**: `sectionId` (UUID)
- **Response**: 204 No Content

#### Bulk Update Catalog Sections
- **Endpoint**: `PATCH /api/admin/catalog/layout/bulk`
- **Body**: `BulkUpdateRequest` - Array of section updates
- **Response**: List of `CatalogSectionDto`

#### Enable/Disable Catalog Section
- **Endpoint**: `PUT /api/admin/catalog/layout/{sectionId}/enabled`
- **Path Parameter**: `sectionId` (UUID)
- **Body**: `{ enabled: boolean }`
- **Response**: `CatalogSectionDto`

---

### 2.2 Category Management

#### Get All Categories for a Section (Admin)
- **Endpoint**: `GET /api/v1/admin/section/{sectionId}/categories`
- **Description**: Returns all categories (enabled and disabled) for a section with sectionCode = PRODUCT_CATEGORY
- **Path Parameter**: `sectionId` (UUID)
- **Response**: `CategorySectionAdminResponseDto` with timestamps

#### Get Category by ID
- **Endpoint**: `GET /api/v1/admin/category/{categoryId}`
- **Path Parameter**: `categoryId` (UUID)
- **Response**: `CategoryAdminDto` with timestamps

#### Create Category
- **Endpoint**: `POST /api/v1/admin/category`
- **Body**: `CreateCategoryRequest` with:
  - `sectionId` (UUID, required)
  - `name` (string, required)
  - `description` (string, optional)
  - `code` (string, required)
  - `displayOrder` (int, required)
- **Response**: `CategoryAdminDto` (201 Created)

#### Update Category
- **Endpoint**: `PUT /api/v1/admin/category/{categoryId}`
- **Path Parameter**: `categoryId` (UUID)
- **Body**: `UpdateCategoryRequest` (partial update)
- **Response**: `CategoryAdminDto`

#### Delete Category
- **Endpoint**: `DELETE /api/v1/admin/category/{categoryId}`
- **Path Parameter**: `categoryId` (UUID)
- **Response**: 204 No Content

#### Bulk Update Categories
- **Endpoint**: `PATCH /api/v1/admin/category/bulk`
- **Body**: `BulkUpdateCategoryRequest` - Array of category updates
- **Response**: List of `CategoryAdminDto`

---

### 2.3 Subcategory Management

#### Get All Subcategories for a Category (Admin)
- **Endpoint**: `GET /api/v1/admin/category/{categoryId}/subCategories`
- **Description**: Returns all subcategories (enabled and disabled) for a category, sorted by displayOrder
- **Path Parameter**: `categoryId` (UUID)
- **Response**: `SubCategoryAdminResponseDto` with timestamps

#### Get Subcategory by ID
- **Endpoint**: `GET /api/v1/admin/category/subCategory/{subCategoryId}`
- **Path Parameter**: `subCategoryId` (UUID)
- **Response**: `SubCategoryAdminDto` with timestamps

#### Create Subcategory
- **Endpoint**: `POST /api/v1/admin/category/subCategory`
- **Body**: `CreateSubCategoryRequest` with:
  - `categoryId` (UUID, required)
  - `name` (string, required)
  - `description` (string, optional)
  - `code` (string, required)
  - `displayOrder` (int, required)
  - `imageUrl[]` (array of strings, optional)
- **Response**: `SubCategoryAdminDto` (201 Created)

#### Update Subcategory
- **Endpoint**: `PUT /api/v1/admin/category/subCategory/{subCategoryId}`
- **Path Parameter**: `subCategoryId` (UUID)
- **Body**: `UpdateSubCategoryRequest` (partial update)
- **Response**: `SubCategoryAdminDto`

#### Delete Subcategory
- **Endpoint**: `DELETE /api/v1/admin/category/subCategory/{subCategoryId}`
- **Path Parameter**: `subCategoryId` (UUID)
- **Response**: 204 No Content

#### Bulk Update Subcategories
- **Endpoint**: `PATCH /api/v1/admin/category/subCategory/bulk`
- **Body**: `BulkUpdateSubCategoryRequest` - Array of subcategory updates
- **Response**: List of `SubCategoryAdminDto`

---

### 2.4 Product Group Management

#### Get All Product Groups for a Subcategory (Admin)
- **Endpoint**: `GET /api/v1/admin/subCategory/{subCategoryId}/productGroups`
- **Description**: Returns all product groups (enabled and disabled) for a subcategory, sorted by displayOrder
- **Path Parameter**: `subCategoryId` (UUID)
- **Response**: `ProductGroupAdminResponseDto` with timestamps

#### Get Product Group by ID
- **Endpoint**: `GET /api/v1/admin/subCategory/productGroup/{productGroupId}`
- **Path Parameter**: `productGroupId` (UUID)
- **Response**: `ProductGroupAdminDto` with timestamps

#### Create Product Group
- **Endpoint**: `POST /api/v1/admin/subCategory/productGroup`
- **Body**: `CreateProductGroupRequest` with:
  - `subCategoryId` (UUID, required)
  - `name` (string, required)
  - `description` (string, optional)
  - `code` (string, required)
  - `displayOrder` (int, required)
  - `imageUrl[]` (array of strings, optional)
- **Response**: `ProductGroupAdminDto` (201 Created)

#### Update Product Group
- **Endpoint**: `PUT /api/v1/admin/subCategory/productGroup/{productGroupId}`
- **Path Parameter**: `productGroupId` (UUID)
- **Body**: `UpdateProductGroupRequest` (partial update)
- **Response**: `ProductGroupAdminDto`

#### Delete Product Group
- **Endpoint**: `DELETE /api/v1/admin/subCategory/productGroup/{productGroupId}`
- **Path Parameter**: `productGroupId` (UUID)
- **Response**: 204 No Content

#### Bulk Update Product Groups
- **Endpoint**: `PATCH /api/v1/admin/subCategory/productGroup/bulk`
- **Body**: `BulkUpdateProductGroupRequest` - Array of product group updates
- **Response**: List of `ProductGroupAdminDto`

---

### 2.5 Product Management

#### Get Product by ID (Admin)
- **Endpoint**: `GET /api/admin/products/{productId}`
- **Description**: Returns a single product by its ID with all details including timestamps
- **Path Parameter**: `productId` (UUID)
- **Response**: `ProductAdminResponseDto`

#### Create Product
- **Endpoint**: `POST /api/admin/products`
- **Description**: Creates a new product. Product will be disabled by default and is not sellable until variants, pricing, and inventory are added.
- **Body**: `ProductCreateRequestDto` with:
  - `productGroupId` (UUID, required)
  - `name` (string, required)
  - `brand` (string, optional)
  - `code` (string, required, unique)
  - `description` (string, optional)
  - `displayOrder` (int, optional)
  - `images` (object with `primary` and `gallery[]`, optional)
- **Response**: `ProductAdminResponseDto` (201 Created)
- **Error Codes**: 
  - 400: Invalid request data or ProductGroup not found
  - 409: Product code already exists

#### Update Product
- **Endpoint**: `PUT /api/admin/products/{productId}`
- **Description**: Updates an existing product. Only provided fields will be updated. Code and productGroupId cannot be changed.
- **Path Parameter**: `productId` (UUID)
- **Body**: `ProductUpdateRequestDto` (partial update)
- **Response**: `ProductAdminResponseDto`
- **Error Codes**: 
  - 404: Product not found
  - 400: Invalid request data

#### Enable/Disable Product
- **Endpoint**: `PUT /api/admin/products/{productId}/enable`
- **Description**: Enables or disables a product. When disabled, product will disappear from user APIs.
- **Path Parameter**: `productId` (UUID)
- **Body**: `{ enabled: boolean }`
- **Response**: `ProductAdminResponseDto`
- **Error Codes**: 
  - 404: Product not found
  - 400: Invalid request data

#### Get Product Listing for Admin
- **Endpoint**: `GET /api/admin/products/productGroup/{productGroupId}/products`
- **Description**: Returns a paginated list of ALL products (enabled and disabled) for a product group. Shows products even if they have no variants, pricing, or inventory.
- **Path Parameter**: `productGroupId` (UUID)
- **Query Parameters**: 
  - `page` (int, default: 0, 0-based)
  - `size` (int, default: 20, max: 100)
- **Response**: `ProductListingResponseDto` with pagination
- **Error Codes**: 
  - 404: ProductGroup not found

---

### 2.6 Variant Management

#### Get All Variants
- **Endpoint**: `GET /api/admin/variants`
- **Description**: Returns a list of all variants (enabled and disabled) in the system
- **Response**: List of `VariantAdminResponseDto`

#### Get Variant by ID
- **Endpoint**: `GET /api/admin/variants/{variantId}`
- **Description**: Returns a single variant by its ID with all details including timestamps
- **Path Parameter**: `variantId` (UUID)
- **Response**: `VariantAdminResponseDto`
- **Error Codes**: 
  - 404: Variant not found

#### Get All Variants for a Product
- **Endpoint**: `GET /api/admin/products/{productId}/variants`
- **Description**: Returns a list of all variants (enabled and disabled) for a specific product
- **Path Parameter**: `productId` (UUID)
- **Response**: List of `VariantAdminResponseDto`
- **Error Codes**: 
  - 404: Product not found

#### Create Variant
- **Endpoint**: `POST /api/admin/products/{productId}/variants`
- **Description**: Creates a new variant for a product. Variant will be disabled by default and is not sellable until pricing and inventory are added.
- **Path Parameter**: `productId` (UUID)
- **Body**: `VariantCreateRequestDto` with:
  - `sku` (string, required, unique)
  - `label` (string, required)
  - `attributes` (object, optional)
  - `isDefault` (boolean, optional)
  - `displayOrder` (int, optional)
- **Response**: `VariantAdminResponseDto` (201 Created)
- **Error Codes**: 
  - 400: Invalid request data or Product not found
  - 409: SKU already exists

#### Update Variant
- **Endpoint**: `PUT /api/admin/variants/{variantId}`
- **Description**: Updates an existing variant. Only provided fields will be updated. SKU and productId cannot be changed.
- **Path Parameter**: `variantId` (UUID)
- **Body**: `VariantUpdateRequestDto` (partial update)
- **Response**: `VariantAdminResponseDto`
- **Error Codes**: 
  - 404: Variant not found
  - 400: Invalid request data

#### Enable/Disable Variant
- **Endpoint**: `PUT /api/admin/variants/{variantId}/enable`
- **Description**: Enables or disables a variant. When disabled, variant will disappear from user APIs.
- **Path Parameter**: `variantId` (UUID)
- **Body**: `{ enabled: boolean }`
- **Response**: `VariantAdminResponseDto`
- **Error Codes**: 
  - 404: Variant not found
  - 400: Invalid request data

---

### 2.7 Pricing Management

#### Get All Pricing Records
- **Endpoint**: `GET /api/admin/pricing`
- **Description**: Returns a list of all pricing records (active and inactive) in the system
- **Response**: List of `PricingAdminResponseDto`

#### Get Pricing by Variant ID
- **Endpoint**: `GET /api/admin/variants/{variantId}/pricing`
- **Description**: Returns a list of all pricing records (active and inactive) for a specific variant
- **Path Parameter**: `variantId` (UUID)
- **Response**: List of `PricingAdminResponseDto`

#### Get Pricing by Product ID
- **Endpoint**: `GET /api/admin/products/{productId}/pricing`
- **Description**: Returns a list of all pricing records (active and inactive) for all variants of a specific product
- **Path Parameter**: `productId` (UUID)
- **Response**: List of `PricingAdminResponseDto`

#### Get Pricing by Product Group ID
- **Endpoint**: `GET /api/admin/product-groups/{productGroupId}/pricing`
- **Description**: Returns a list of all pricing records (active and inactive) for all variants of all products in a specific product group
- **Path Parameter**: `productGroupId` (UUID)
- **Response**: List of `PricingAdminResponseDto`

#### Create Pricing
- **Endpoint**: `POST /api/admin/variants/{variantId}/pricing`
- **Description**: Creates a new pricing for a variant. If an active pricing already exists, it will be deactivated. Only one active pricing per variant is allowed.
- **Path Parameter**: `variantId` (UUID)
- **Body**: `PricingCreateRequestDto` with:
  - `sellingPrice` (decimal, required)
  - `mrp` (decimal, required)
  - `currency` (string, required, e.g., "INR")
  - `active` (boolean, optional, default: true)
- **Response**: `PricingAdminResponseDto` (201 Created)
- **Error Codes**: 
  - 400: Invalid request data, variant not found, or variant is disabled
  - 409: Duplicate active pricing conflict

#### Update Pricing
- **Endpoint**: `PUT /api/admin/pricing/{pricingId}`
- **Description**: Updates an existing pricing. Only provided fields will be updated. Variant ID and currency cannot be changed. If activating pricing, ensures no other active pricing exists for the variant.
- **Path Parameter**: `pricingId` (UUID)
- **Body**: `PricingUpdateRequestDto` (partial update)
- **Response**: `PricingAdminResponseDto`
- **Error Codes**: 
  - 404: Pricing not found
  - 400: Invalid request data or validation failed
  - 409: Duplicate active pricing conflict

---

### 2.8 Inventory Management

#### Get All Inventories
- **Endpoint**: `GET /api/admin/inventory`
- **Description**: Returns a list of all inventory records in the system
- **Response**: List of `InventoryAdminResponseDto`

#### Get Inventory by ID
- **Endpoint**: `GET /api/admin/inventory/{inventoryId}`
- **Description**: Returns a single inventory record by its ID
- **Path Parameter**: `inventoryId` (UUID)
- **Response**: `InventoryAdminResponseDto`
- **Error Codes**: 
  - 404: Inventory not found

#### Get Inventories by Variant ID
- **Endpoint**: `GET /api/admin/variants/{variantId}/inventory`
- **Description**: Returns all inventory records for a variant. A variant can have multiple inventory records for different warehouses.
- **Path Parameter**: `variantId` (UUID)
- **Response**: List of `InventoryAdminResponseDto`
- **Error Codes**: 
  - 404: Variant not found

#### Get Inventories by Product ID
- **Endpoint**: `GET /api/admin/products/{productId}/inventory`
- **Description**: Returns all inventory records for all variants of a product
- **Path Parameter**: `productId` (UUID)
- **Response**: List of `InventoryAdminResponseDto`
- **Error Codes**: 
  - 404: Product not found

#### Create Inventory
- **Endpoint**: `POST /api/admin/variants/{variantId}/inventory`
- **Description**: Creates inventory for a variant. Variant must exist and be enabled. Inventory determines whether a variant is purchasable. If inventory already exists for the variant and warehouse combination, returns conflict error.
- **Path Parameter**: `variantId` (UUID)
- **Body**: `InventoryCreateRequestDto` with:
  - `warehouseId` (UUID, required)
  - `quantity` (int, required)
  - `reservedQuantity` (int, optional, default: 0)
- **Response**: `InventoryAdminResponseDto` (201 Created)
- **Error Codes**: 
  - 400: Invalid request data or variant not found/disabled
  - 409: Inventory already exists for variant and warehouse

#### Update Inventory
- **Endpoint**: `PUT /api/admin/inventory/{inventoryId}`
- **Description**: Updates existing inventory. Only quantity and reservedQuantity can be updated. warehouseId and variantId cannot be changed. availableQuantity and inStock are automatically recalculated.
- **Path Parameter**: `inventoryId` (UUID)
- **Body**: `InventoryUpdateRequestDto` with:
  - `quantity` (int, required)
  - `reservedQuantity` (int, optional)
- **Response**: `InventoryAdminResponseDto`
- **Error Codes**: 
  - 404: Inventory not found
  - 400: Invalid request data (e.g., quantity < reservedQuantity)

---

## 3. Important Notes for Flutter Development

### 3.1 Data Flow Hierarchy
The data structure follows this hierarchy:
1. **Catalog Layout** → Sections
2. **Categories** → Belong to sections
3. **Subcategories** → Belong to categories
4. **Product Groups** → Belong to subcategories
5. **Products** → Belong to product groups
6. **Variants** → Belong to products
7. **Pricing** → Belongs to variants
8. **Inventory** → Belongs to variants

### 3.2 Key Concepts

#### Enabled/Disabled Status
- **User APIs**: Only return enabled items
- **Admin APIs**: Return all items (enabled and disabled)
- When an item is disabled, it disappears from user-facing APIs

#### Pagination
- Product listing APIs support pagination
- Parameters: `page` (0-based, default: 0), `size` (default: 20, max: 100)
- Response includes pagination metadata: `totalElements`, `totalPages`, `hasNext`, `hasPrevious`

#### UUIDs
- All IDs are UUIDs (use `String` type in Flutter/Dart)
- Format: `"a1b2c3d4-e5f6-4789-a012-345678901234"`

#### Default Variant
- Each product has a default variant used for listing display
- Set via `isDefault: true` when creating/updating variants

#### Active Pricing
- Only one active pricing per variant is allowed
- Creating new active pricing automatically deactivates existing active pricing
- User APIs only show variants with active pricing

#### Inventory
- Variants can have multiple inventory records (different warehouses)
- `availableQuantity = quantity - reservedQuantity`
- `inStock = availableQuantity > 0`

### 3.3 Error Handling

#### HTTP Status Codes
- **200 OK**: Success
- **201 Created**: Resource created successfully
- **204 No Content**: Success with no content (DELETE operations)
- **400 Bad Request**: Invalid request data or validation failed
- **404 Not Found**: Resource not found
- **409 Conflict**: Duplicate resource (code/SKU already exists, inventory already exists, etc.)

#### Error Response Format
Check the `GlobalExceptionHandler` for standard error response format. Typically includes:
- Error code
- Error message
- Timestamp

### 3.4 Swagger Documentation
- **Swagger UI**: Access at `/swagger-ui.html` when server is running
- **API Docs**: Available at `/api-docs`
- Use Swagger UI to test APIs and see request/response schemas

### 3.5 Development Workflow

#### For Customer App (User APIs)
1. Start with catalog layout to get sections
2. Get categories for each section
3. Get subcategories for selected category
4. Get product groups for selected subcategory
5. Get product listing for selected product group
6. Get product details when user selects a product

#### For Admin Panel (Admin APIs)
1. Manage catalog layout sections
2. Manage categories within sections
3. Manage subcategories within categories
4. Manage product groups within subcategories
5. Manage products within product groups
6. Manage variants for products
7. Add pricing for variants
8. Add inventory for variants

### 3.6 Product Creation Flow (Admin)
To make a product sellable, follow this order:
1. Create product (disabled by default)
2. Create variant(s) for the product (disabled by default)
3. Enable variant(s)
4. Add pricing for variant(s) (must be active)
5. Add inventory for variant(s)
6. Enable product

Only then will the product appear in user APIs with proper pricing and availability.

---

## 4. Sample Request/Response Examples

### User API Example: Get Product Listing
```http
GET /api/v1/productGroup/a1a2b3c4-d5e6-4789-a012-345678901234/products?page=0&size=20
```

**Response:**
```json
{
  "productGroupId": "a1a2b3c4-d5e6-4789-a012-345678901234",
  "productGroupName": "Fresh Fruits",
  "products": [
    {
      "productId": "f1a2b3c4-d5e6-4789-f012-345678901234",
      "name": "Apple - Red Delicious",
      "brand": "Fresh Farm",
      "image": {
        "primary": "https://example.com/apple.jpg"
      },
      "defaultVariant": {
        "variantId": "b1a2b3c4-d5e6-4789-b012-345678901234",
        "label": "1 kg"
      },
      "pricing": {
        "sellingPrice": 80.0,
        "mrp": 100.0,
        "discountPercent": 20,
        "currency": "INR"
      },
      "availability": {
        "inStock": true
      }
    }
  ],
  "pagination": {
    "page": 0,
    "size": 20,
    "totalElements": 45,
    "totalPages": 3,
    "hasNext": true,
    "hasPrevious": false
  }
}
```

### Admin API Example: Create Product
```http
POST /api/admin/products
Content-Type: application/json

{
  "productGroupId": "a1a2b3c4-d5e6-4789-a012-345678901234",
  "name": "Apple - Red Delicious",
  "brand": "Fresh Farm",
  "code": "APPLE_RED_DELICIOUS",
  "description": "Fresh red delicious apples",
  "displayOrder": 1,
  "images": {
    "primary": "https://example.com/apple.jpg",
    "gallery": ["https://example.com/apple2.jpg"]
  }
}
```

**Response (201 Created):**
```json
{
  "productId": "f1a2b3c4-d5e6-4789-f012-345678901234",
  "productGroupId": "a1a2b3c4-d5e6-4789-a012-345678901234",
  "name": "Apple - Red Delicious",
  "brand": "Fresh Farm",
  "code": "APPLE_RED_DELICIOUS",
  "description": "Fresh red delicious apples",
  "displayOrder": 1,
  "enabled": false,
  "images": {
    "primary": "https://example.com/apple.jpg",
    "gallery": ["https://example.com/apple2.jpg"]
  },
  "createdAt": "2026-01-17T10:35:00.000000",
  "updatedAt": "2026-01-17T10:35:00.000000"
}
```

---

## 5. Quick Reference

### User API Endpoints Summary
| Endpoint | Method | Description |
|----------|--------|-------------|
| `/api/user/catalog/layout` | GET | Get enabled catalog sections |
| `/api/v1/section/{sectionId}/categories` | GET | Get enabled categories |
| `/api/v1/category/{categoryId}/subCategories` | GET | Get enabled subcategories |
| `/api/v1/productGroup/{productGroupId}/products` | GET | Get product listing (paginated) |
| `/api/v1/product/{productId}` | GET | Get product details (PDP) |

### Admin API Endpoints Summary
| Category | Endpoints |
|----------|-----------|
| **Catalog Layout** | `/api/admin/catalog/layout` (GET, POST, PUT, DELETE, PATCH) |
| **Categories** | `/api/v1/admin/section/{id}/categories` (GET), `/api/v1/admin/category` (POST, PUT, DELETE, PATCH) |
| **Subcategories** | `/api/v1/admin/category/{id}/subCategories` (GET), `/api/v1/admin/category/subCategory` (POST, PUT, DELETE, PATCH) |
| **Product Groups** | `/api/v1/admin/subCategory/{id}/productGroups` (GET), `/api/v1/admin/subCategory/productGroup` (POST, PUT, DELETE, PATCH) |
| **Products** | `/api/admin/products` (GET, POST, PUT) |
| **Variants** | `/api/admin/variants` (GET), `/api/admin/products/{id}/variants` (GET, POST), `/api/admin/variants/{id}` (PUT) |
| **Pricing** | `/api/admin/pricing` (GET), `/api/admin/variants/{id}/pricing` (GET, POST), `/api/admin/pricing/{id}` (PUT) |
| **Inventory** | `/api/admin/inventory` (GET), `/api/admin/variants/{id}/inventory` (GET, POST), `/api/admin/inventory/{id}` (PUT) |

---

**Last Updated**: Based on current codebase structure
**Backend Framework**: Spring Boot (Java)
**Database**: H2 (development), PostgreSQL (production)



