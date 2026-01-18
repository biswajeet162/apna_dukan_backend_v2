-- Sample data for Catalog Layout Sections
-- Using UUID strings directly (H2 supports UUID natively)

-- Featured Products Section
INSERT INTO catalog_section (section_id, section_code, title, description, layout_type, scroll_type, display_order, enabled, personalized, created_at, updated_at)
VALUES (
    '550e8400-e29b-41d4-a716-446655440001',
    'FEATURED',
    'Featured Products',
    'Handpicked featured products for you',
    'SINGLE_ROW',
    'HORIZONTAL',
    1,
    true,
    false,
    CURRENT_TIMESTAMP,
    CURRENT_TIMESTAMP
);

-- New Arrivals Section
INSERT INTO catalog_section (section_id, section_code, title, description, layout_type, scroll_type, display_order, enabled, personalized, created_at, updated_at)
VALUES (
    '550e8400-e29b-41d4-a716-446655440002',
    'NEW_ARRIVALS',
    'New Arrivals',
    'Latest products just arrived',
    'SINGLE_ROW',
    'HORIZONTAL',
    2,
    true,
    false,
    CURRENT_TIMESTAMP,
    CURRENT_TIMESTAMP
);

-- Best Sellers Section
INSERT INTO catalog_section (section_id, section_code, title, description, layout_type, scroll_type, display_order, enabled, personalized, created_at, updated_at)
VALUES (
    '550e8400-e29b-41d4-a716-446655440003',
    'BEST_SELLERS',
    'Best Sellers',
    'Top selling products this week',
    'SINGLE_ROW',
    'HORIZONTAL',
    3,
    true,
    false,
    CURRENT_TIMESTAMP,
    CURRENT_TIMESTAMP
);

-- Trending Products Section
INSERT INTO catalog_section (section_id, section_code, title, description, layout_type, scroll_type, display_order, enabled, personalized, created_at, updated_at)
VALUES (
    '550e8400-e29b-41d4-a716-446655440004',
    'TRENDING',
    'Trending Now',
    'Products trending right now',
    'SINGLE_ROW',
    'HORIZONTAL',
    4,
    true,
    true,
    CURRENT_TIMESTAMP,
    CURRENT_TIMESTAMP
);

-- Flash Deals Section
INSERT INTO catalog_section (section_id, section_code, title, description, layout_type, scroll_type, display_order, enabled, personalized, created_at, updated_at)
VALUES (
    '550e8400-e29b-41d4-a716-446655440005',
    'FLASH_DEALS',
    'Flash Deals',
    'Limited time offers and deals',
    'SINGLE_ROW',
    'HORIZONTAL',
    5,
    true,
    false,
    CURRENT_TIMESTAMP,
    CURRENT_TIMESTAMP
);

-- Product Categories Section
INSERT INTO catalog_section (section_id, section_code, title, description, layout_type, scroll_type, display_order, enabled, personalized, created_at, updated_at)
VALUES (
    '550e8400-e29b-41d4-a716-446655440006',
    'PRODUCT_CATEGORY',
    'Shop by Category',
    'Browse products by category',
    'SINGLE_ROW',
    'HORIZONTAL',
    6,
    true,
    false,
    CURRENT_TIMESTAMP,
    CURRENT_TIMESTAMP
);

-- Banner Section
INSERT INTO catalog_section (section_id, section_code, title, description, layout_type, scroll_type, display_order, enabled, personalized, created_at, updated_at)
VALUES (
    '550e8400-e29b-41d4-a716-446655440007',
    'BANNER',
    'Promotional Banners',
    'Special offers and promotions',
    'SINGLE_ROW',
    'HORIZONTAL',
    7,
    true,
    false,
    CURRENT_TIMESTAMP,
    CURRENT_TIMESTAMP
);

-- Recommended For You Section (Personalized)
INSERT INTO catalog_section (section_id, section_code, title, description, layout_type, scroll_type, display_order, enabled, personalized, created_at, updated_at)
VALUES (
    '550e8400-e29b-41d4-a716-446655440008',
    'RECOMMENDED',
    'Recommended For You',
    'Personalized recommendations based on your preferences',
    'SINGLE_ROW',
    'HORIZONTAL',
    8,
    true,
    true,
    CURRENT_TIMESTAMP,
    CURRENT_TIMESTAMP
);

-- Recently Viewed Section
INSERT INTO catalog_section (section_id, section_code, title, description, layout_type, scroll_type, display_order, enabled, personalized, created_at, updated_at)
VALUES (
    '550e8400-e29b-41d4-a716-446655440009',
    'RECENTLY_VIEWED',
    'Recently Viewed',
    'Products you recently viewed',
    'SINGLE_ROW',
    'HORIZONTAL',
    9,
    true,
    true,
    CURRENT_TIMESTAMP,
    CURRENT_TIMESTAMP
);

-- Ads Section (Disabled for testing - should not appear in user API)
INSERT INTO catalog_section (section_id, section_code, title, description, layout_type, scroll_type, display_order, enabled, personalized, created_at, updated_at)
VALUES (
    '550e8400-e29b-41d4-a716-446655440010',
    'ADS',
    'Advertisement Section',
    'Sponsored advertisements',
    'SINGLE_ROW',
    'HORIZONTAL',
    10,
    false,
    false,
    CURRENT_TIMESTAMP,
    CURRENT_TIMESTAMP
);

