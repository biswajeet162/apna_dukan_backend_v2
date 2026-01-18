-- Catalog Layout Sections Data
-- Using UUID strings directly (H2 supports UUID natively)

INSERT INTO catalog_section (section_id, section_code, title, description, layout_type, scroll_type, display_order, enabled, personalized, created_at, updated_at)
VALUES ('768d4ee5-0ef7-458d-9748-409260cf93aa', 'PRODUCT_CATEGORY', 'Category', 'Category section for displaying product categories', 'SINGLE_ROW', 'HORIZONTAL', 1, true, false, TIMESTAMP '2026-01-17 10:30:42.023172', TIMESTAMP '2026-01-17 10:30:42.023172');

INSERT INTO catalog_section (section_id, section_code, title, description, layout_type, scroll_type, display_order, enabled, personalized, created_at, updated_at)
VALUES ('d2aa248d-b67a-4a78-a001-4779711dcb14', 'BANNER', 'Banner', 'Banner section for promotional banners', 'SINGLE_ROW', 'HORIZONTAL', 2, true, false, TIMESTAMP '2026-01-17 10:30:42.035472', TIMESTAMP '2026-01-17 10:30:42.035472');

INSERT INTO catalog_section (section_id, section_code, title, description, layout_type, scroll_type, display_order, enabled, personalized, created_at, updated_at)
VALUES ('369c5104-ad24-44d0-b9e0-f55a125c1330', 'RECENTLY_VIEWED', 'Recently Viewed', 'Recently viewed products by the user', 'SINGLE_ROW', 'HORIZONTAL', 3, true, true, TIMESTAMP '2026-01-17 10:30:42.035472', TIMESTAMP '2026-01-17 10:30:42.035472');

INSERT INTO catalog_section (section_id, section_code, title, description, layout_type, scroll_type, display_order, enabled, personalized, created_at, updated_at)
VALUES ('c18747f2-7f42-4976-a485-19f97940ceb4', 'ADS', 'Ads', 'for showing added on sale', 'SINGLE_ROW', 'HORIZONTAL', 4, true, false, TIMESTAMP '2026-01-17 10:30:42.035472', TIMESTAMP '2026-01-17 10:30:42.035472');

INSERT INTO catalog_section (section_id, section_code, title, description, layout_type, scroll_type, display_order, enabled, personalized, created_at, updated_at)
VALUES ('91571619-aa50-4c37-988a-409f0475b62b', 'HIGHLIGHT', 'Highlight', 'Highlighted products section', 'SINGLE_ROW', 'HORIZONTAL', 5, false, false, TIMESTAMP '2026-01-17 10:30:42.0405', TIMESTAMP '2026-01-17 10:30:42.0405');

INSERT INTO catalog_section (section_id, section_code, title, description, layout_type, scroll_type, display_order, enabled, personalized, created_at, updated_at)
VALUES ('712a0407-89a1-43f3-95e9-c63549ae9f7d', 'SPONSORED', 'Sponsored', 'Sponsored banner section', 'SINGLE_ROW', 'HORIZONTAL', 6, false, false, TIMESTAMP '2026-01-17 10:30:42.04161', TIMESTAMP '2026-01-17 10:30:42.04161');

INSERT INTO catalog_section (section_id, section_code, title, description, layout_type, scroll_type, display_order, enabled, personalized, created_at, updated_at)
VALUES ('019dab39-6c52-4700-8352-5f2c59a839f1', 'PRODUCT_GRID', 'Product Grid', 'Product grid with 2 rows layout', 'TWO_ROW', 'HORIZONTAL', 7, false, false, TIMESTAMP '2026-01-17 10:30:42.043618', TIMESTAMP '2026-01-17 10:30:42.043618');

INSERT INTO catalog_section (section_id, section_code, title, description, layout_type, scroll_type, display_order, enabled, personalized, created_at, updated_at)
VALUES ('7f3568af-30f5-408e-aa43-59afa4f99855', 'BUDGET_DEALS', 'Budget Deals', 'Budget deals and offers', 'SINGLE_ROW', 'HORIZONTAL', 8, false, false, TIMESTAMP '2026-01-17 10:30:42.043618', TIMESTAMP '2026-01-17 10:30:42.043618');

INSERT INTO catalog_section (section_id, section_code, title, description, layout_type, scroll_type, display_order, enabled, personalized, created_at, updated_at)
VALUES ('c491f2b9-e433-48bf-b397-8465cf3a7775', 'RECOMMENDED', 'Recommended', 'Recommended products for the user', 'SINGLE_ROW', 'HORIZONTAL', 9, true, true, TIMESTAMP '2026-01-17 10:30:42.04563', TIMESTAMP '2026-01-17 10:30:42.04563');

INSERT INTO catalog_section (section_id, section_code, title, description, layout_type, scroll_type, display_order, enabled, personalized, created_at, updated_at)
VALUES ('5f82185c-2955-4cd0-a13c-13edafee3d4b', 'CATEGORY_PRODUCTS', 'Category Products', 'Products by category', 'SINGLE_ROW', 'HORIZONTAL', 10, false, false, TIMESTAMP '2026-01-17 10:30:42.04563', TIMESTAMP '2026-01-17 10:30:42.04563');

INSERT INTO catalog_section (section_id, section_code, title, description, layout_type, scroll_type, display_order, enabled, personalized, created_at, updated_at)
VALUES ('ea1bcf96-b7da-458d-8da8-f2b1acffe55d', 'FEATURED', 'Featured', 'Featured products list', 'SINGLE_ROW', 'HORIZONTAL', 11, true, false, TIMESTAMP '2026-01-17 10:30:42.047638', TIMESTAMP '2026-01-17 10:30:42.047638');

INSERT INTO catalog_section (section_id, section_code, title, description, layout_type, scroll_type, display_order, enabled, personalized, created_at, updated_at)
VALUES ('3a09dca3-67cd-4fb4-a5e4-8cf5da7c0fdc', 'TRENDING', 'Trending Now', 'Trending products section', 'SINGLE_ROW', 'HORIZONTAL', 12, true, false, TIMESTAMP '2026-01-17 10:30:42.047638', TIMESTAMP '2026-01-17 10:30:42.049645');

INSERT INTO catalog_section (section_id, section_code, title, description, layout_type, scroll_type, display_order, enabled, personalized, created_at, updated_at)
VALUES ('8dba7b7b-fd8d-4ba3-bd83-ef05ceb741af', 'NEW_ARRIVALS', 'New Arrivals', 'Newly arrived products', 'SINGLE_ROW', 'HORIZONTAL', 13, true, false, TIMESTAMP '2026-01-17 10:30:42.049645', TIMESTAMP '2026-01-17 10:30:42.049645');

INSERT INTO catalog_section (section_id, section_code, title, description, layout_type, scroll_type, display_order, enabled, personalized, created_at, updated_at)
VALUES ('9811fbaa-515d-4dba-8cda-0ebcc1051dc6', 'BEST_SELLERS', 'Best Sellers', 'Best selling products', 'SINGLE_ROW', 'HORIZONTAL', 14, true, false, TIMESTAMP '2026-01-17 10:30:42.051393', TIMESTAMP '2026-01-17 10:30:42.051393');

INSERT INTO catalog_section (section_id, section_code, title, description, layout_type, scroll_type, display_order, enabled, personalized, created_at, updated_at)
VALUES ('e95d5a16-26f2-410f-ad7c-960229358b86', 'TOP_RATED', 'Top Rated', 'Top rated products', 'SINGLE_ROW', 'HORIZONTAL', 15, false, false, TIMESTAMP '2026-01-17 10:30:42.053404', TIMESTAMP '2026-01-17 10:30:42.053404');

INSERT INTO catalog_section (section_id, section_code, title, description, layout_type, scroll_type, display_order, enabled, personalized, created_at, updated_at)
VALUES ('3e4b249b-f3f2-405c-8a85-2ecebb0e564a', 'CONTINUE_SHOPPING', 'Continue Shopping', 'Continue shopping from where you left', 'SINGLE_ROW', 'HORIZONTAL', 16, false, true, TIMESTAMP '2026-01-17 10:30:42.053404', TIMESTAMP '2026-01-17 10:30:42.053404');

INSERT INTO catalog_section (section_id, section_code, title, description, layout_type, scroll_type, display_order, enabled, personalized, created_at, updated_at)
VALUES ('d953276e-62cc-4d79-b93a-6340fecab807', 'RELATED', 'Related Products', 'Products related to your interests', 'SINGLE_ROW', 'HORIZONTAL', 17, false, true, TIMESTAMP '2026-01-17 10:30:42.053404', TIMESTAMP '2026-01-17 10:30:42.053404');

INSERT INTO catalog_section (section_id, section_code, title, description, layout_type, scroll_type, display_order, enabled, personalized, created_at, updated_at)
VALUES ('2cd372e6-a9dc-4596-957f-ed905e03e459', 'FLASH_DEALS', 'Flash Deals', 'Limited time flash deals', 'SINGLE_ROW', 'HORIZONTAL', 18, true, false, TIMESTAMP '2026-01-17 10:30:42.053404', TIMESTAMP '2026-01-17 10:30:42.053404');

INSERT INTO catalog_section (section_id, section_code, title, description, layout_type, scroll_type, display_order, enabled, personalized, created_at, updated_at)
VALUES ('94f00903-74ac-40e3-8c2c-7bf28cce8d0c', 'LIMITED_OFFERS', 'Limited Offers', 'Limited time offers', 'SINGLE_ROW', 'HORIZONTAL', 19, false, false, TIMESTAMP '2026-01-17 10:30:42.053404', TIMESTAMP '2026-01-17 10:30:42.053404');

INSERT INTO catalog_section (section_id, section_code, title, description, layout_type, scroll_type, display_order, enabled, personalized, created_at, updated_at)
VALUES ('82deea78-bd4d-4353-88a9-7476dad07594', 'PRICE_DROP', 'Price Drop', 'Products with price drops', 'SINGLE_ROW', 'HORIZONTAL', 20, false, false, TIMESTAMP '2026-01-17 10:30:42.053404', TIMESTAMP '2026-01-17 10:30:42.053404');

INSERT INTO catalog_section (section_id, section_code, title, description, layout_type, scroll_type, display_order, enabled, personalized, created_at, updated_at)
VALUES ('e51ddb59-63ad-4296-be5f-b2781ce18158', 'CLEARANCE', 'Clearance', 'Clearance sale products', 'SINGLE_ROW', 'HORIZONTAL', 21, false, false, TIMESTAMP '2026-01-17 10:30:42.053404', TIMESTAMP '2026-01-17 10:30:42.053404');

INSERT INTO catalog_section (section_id, section_code, title, description, layout_type, scroll_type, display_order, enabled, personalized, created_at, updated_at)
VALUES ('467398cc-2925-4ace-896a-85d16f887add', 'COMBO_DEALS', 'Combo Deals', 'Combo deal offers', 'SINGLE_ROW', 'HORIZONTAL', 22, false, false, TIMESTAMP '2026-01-17 10:30:42.053404', TIMESTAMP '2026-01-17 10:30:42.053404');

INSERT INTO catalog_section (section_id, section_code, title, description, layout_type, scroll_type, display_order, enabled, personalized, created_at, updated_at)
VALUES ('5b27d8d4-569e-4f7f-8858-6f01cffd064a', 'BANK_OFFERS', 'Bank Offers', 'Bank offers and discounts', 'SINGLE_ROW', 'HORIZONTAL', 23, false, false, TIMESTAMP '2026-01-17 10:30:42.053404', TIMESTAMP '2026-01-17 10:30:42.053404');

INSERT INTO catalog_section (section_id, section_code, title, description, layout_type, scroll_type, display_order, enabled, personalized, created_at, updated_at)
VALUES ('7616f878-5927-4b01-ab2a-0b96e2bd4016', 'TOP_BRANDS', 'Top Brands', 'Top brands showcase', 'SINGLE_ROW', 'HORIZONTAL', 24, false, false, TIMESTAMP '2026-01-17 10:30:42.053404', TIMESTAMP '2026-01-17 10:30:42.053404');

INSERT INTO catalog_section (section_id, section_code, title, description, layout_type, scroll_type, display_order, enabled, personalized, created_at, updated_at)
VALUES ('5b60e759-fb3b-4e38-90a4-5c2f24b9ef97', 'BRAND_SPOTLIGHT', 'Brand Spotlight', 'Featured brand spotlight', 'SINGLE_ROW', 'HORIZONTAL', 25, false, false, TIMESTAMP '2026-01-17 10:30:42.053404', TIMESTAMP '2026-01-17 10:30:42.053404');

INSERT INTO catalog_section (section_id, section_code, title, description, layout_type, scroll_type, display_order, enabled, personalized, created_at, updated_at)
VALUES ('bc66a6d7-f8f3-4804-a3a7-53bc37da29be', 'SELLER_SPOTLIGHT', 'Seller Spotlight', 'Featured seller spotlight', 'SINGLE_ROW', 'HORIZONTAL', 26, false, false, TIMESTAMP '2026-01-17 10:30:42.064605', TIMESTAMP '2026-01-17 10:30:42.064605');

INSERT INTO catalog_section (section_id, section_code, title, description, layout_type, scroll_type, display_order, enabled, personalized, created_at, updated_at)
VALUES ('e830bcf9-c316-43b6-8d03-1bbb7f3c9fb5', 'INFLUENCER_PICKS', 'Influencer Picks', 'Products picked by influencers', 'SINGLE_ROW', 'HORIZONTAL', 27, false, false, TIMESTAMP '2026-01-17 10:30:42.064605', TIMESTAMP '2026-01-17 10:30:42.064605');

INSERT INTO catalog_section (section_id, section_code, title, description, layout_type, scroll_type, display_order, enabled, personalized, created_at, updated_at)
VALUES ('fcc8b68a-799d-4082-a5b2-51743363555d', 'VIDEO_PRODUCTS', 'Video Products', 'Products with video content', 'SINGLE_ROW', 'HORIZONTAL', 28, false, false, TIMESTAMP '2026-01-17 10:30:42.064605', TIMESTAMP '2026-01-17 10:30:42.064605');

INSERT INTO catalog_section (section_id, section_code, title, description, layout_type, scroll_type, display_order, enabled, personalized, created_at, updated_at)
VALUES ('39e95ba2-78e9-4223-91f9-fd0d1dc2a2af', 'FAST_DELIVERY', 'Fast Delivery', 'Products with fast delivery option', 'SINGLE_ROW', 'HORIZONTAL', 29, false, false, TIMESTAMP '2026-01-17 10:30:42.068194', TIMESTAMP '2026-01-17 10:30:42.068194');

INSERT INTO catalog_section (section_id, section_code, title, description, layout_type, scroll_type, display_order, enabled, personalized, created_at, updated_at)
VALUES ('b300cb5d-3dd9-438d-bbce-269fef321287', 'BACK_IN_STOCK', 'Back In Stock', 'Products back in stock', 'SINGLE_ROW', 'HORIZONTAL', 30, false, false, TIMESTAMP '2026-01-17 10:30:42.068194', TIMESTAMP '2026-01-17 10:30:42.068194');

INSERT INTO catalog_section (section_id, section_code, title, description, layout_type, scroll_type, display_order, enabled, personalized, created_at, updated_at)
VALUES ('85808d72-471d-4ea7-9a76-64f6aab99c84', 'APP_EXCLUSIVE', 'App Exclusive', 'Exclusive offers for app users', 'SINGLE_ROW', 'HORIZONTAL', 31, false, false, TIMESTAMP '2026-01-17 10:30:42.068194', TIMESTAMP '2026-01-17 10:30:42.068194');

INSERT INTO catalog_section (section_id, section_code, title, description, layout_type, scroll_type, display_order, enabled, personalized, created_at, updated_at)
VALUES ('c13082fa-6c22-4a1f-85d5-4278116e56e6', 'GIFT_IDEAS', 'Gift Ideas', 'Gift ideas and suggestions', 'SINGLE_ROW', 'HORIZONTAL', 32, false, false, TIMESTAMP '2026-01-17 10:30:42.070199', TIMESTAMP '2026-01-17 10:30:42.070199');

INSERT INTO catalog_section (section_id, section_code, title, description, layout_type, scroll_type, display_order, enabled, personalized, created_at, updated_at)
VALUES ('50c824f5-84a0-45d2-87d4-3344076f7bd2', 'AI_RECOMMENDED', 'Ai Recommended', 'AI recommended products', 'SINGLE_ROW', 'HORIZONTAL', 33, false, true, TIMESTAMP '2026-01-17 10:30:42.070199', TIMESTAMP '2026-01-17 10:30:42.070199');

INSERT INTO catalog_section (section_id, section_code, title, description, layout_type, scroll_type, display_order, enabled, personalized, created_at, updated_at)
VALUES ('ff78373f-c3e5-4073-a3b2-4b74b9b60fd7', 'COLLAB_FILTER', 'Collab Filter', 'Collaborative filtering recommendations', 'SINGLE_ROW', 'HORIZONTAL', 34, false, true, TIMESTAMP '2026-01-17 10:30:42.070199', TIMESTAMP '2026-01-17 10:30:42.070199');

-- Category Data
-- All categories are linked to the PRODUCT_CATEGORY section (section_id: 768d4ee5-0ef7-458d-9748-409260cf93aa)

INSERT INTO category (category_id, section_id, name, description, code, display_order, enabled, created_at, updated_at)
VALUES ('a1b2c3d4-e5f6-4789-a012-345678901234', '768d4ee5-0ef7-458d-9748-409260cf93aa', 'Grocery', 'Fresh fruits, vegetables, dairy products, and daily essentials', 'GROCERY', 1, true, TIMESTAMP '2026-01-17 10:35:00.000000', TIMESTAMP '2026-01-17 10:35:00.000000');

INSERT INTO category (category_id, section_id, name, description, code, display_order, enabled, created_at, updated_at)
VALUES ('b2c3d4e5-f6a7-4890-b123-456789012345', '768d4ee5-0ef7-458d-9748-409260cf93aa', 'Electronics', 'Mobile phones, laptops, gadgets, and electronic accessories', 'ELECTRONICS', 2, true, TIMESTAMP '2026-01-17 10:35:00.000000', TIMESTAMP '2026-01-17 10:35:00.000000');

INSERT INTO category (category_id, section_id, name, description, code, display_order, enabled, created_at, updated_at)
VALUES ('c3d4e5f6-a7b8-4901-c234-567890123456', '768d4ee5-0ef7-458d-9748-409260cf93aa', 'Fashion', 'Clothing, shoes, accessories, and fashion items for men and women', 'FASHION', 3, true, TIMESTAMP '2026-01-17 10:35:00.000000', TIMESTAMP '2026-01-17 10:35:00.000000');

INSERT INTO category (category_id, section_id, name, description, code, display_order, enabled, created_at, updated_at)
VALUES ('d4e5f6a7-b8c9-4012-d345-678901234567', '768d4ee5-0ef7-458d-9748-409260cf93aa', 'Home & Kitchen', 'Home decor, kitchen appliances, furniture, and household items', 'HOME_KITCHEN', 4, true, TIMESTAMP '2026-01-17 10:35:00.000000', TIMESTAMP '2026-01-17 10:35:00.000000');

INSERT INTO category (category_id, section_id, name, description, code, display_order, enabled, created_at, updated_at)
VALUES ('e5f6a7b8-c9d0-4123-e456-789012345678', '768d4ee5-0ef7-458d-9748-409260cf93aa', 'Beauty & Personal Care', 'Skincare, makeup, personal hygiene, and beauty products', 'BEAUTY_CARE', 5, true, TIMESTAMP '2026-01-17 10:35:00.000000', TIMESTAMP '2026-01-17 10:35:00.000000');

INSERT INTO category (category_id, section_id, name, description, code, display_order, enabled, created_at, updated_at)
VALUES ('f6a7b8c9-d0e1-4234-f567-890123456789', '768d4ee5-0ef7-458d-9748-409260cf93aa', 'Sports & Fitness', 'Sports equipment, fitness gear, outdoor activities, and gym accessories', 'SPORTS_FITNESS', 6, true, TIMESTAMP '2026-01-17 10:35:00.000000', TIMESTAMP '2026-01-17 10:35:00.000000');

INSERT INTO category (category_id, section_id, name, description, code, display_order, enabled, created_at, updated_at)
VALUES ('a7b8c9d0-e1f2-4345-a678-901234567890', '768d4ee5-0ef7-458d-9748-409260cf93aa', 'Books & Media', 'Books, magazines, movies, music, and educational content', 'BOOKS_MEDIA', 7, true, TIMESTAMP '2026-01-17 10:35:00.000000', TIMESTAMP '2026-01-17 10:35:00.000000');

INSERT INTO category (category_id, section_id, name, description, code, display_order, enabled, created_at, updated_at)
VALUES ('b8c9d0e1-f2a3-4456-b789-012345678901', '768d4ee5-0ef7-458d-9748-409260cf93aa', 'Toys & Games', 'Toys for kids, board games, puzzles, and entertainment items', 'TOYS_GAMES', 8, true, TIMESTAMP '2026-01-17 10:35:00.000000', TIMESTAMP '2026-01-17 10:35:00.000000');

INSERT INTO category (category_id, section_id, name, description, code, display_order, enabled, created_at, updated_at)
VALUES ('c9d0e1f2-a3b4-4567-c890-123456789012', '768d4ee5-0ef7-458d-9748-409260cf93aa', 'Automotive', 'Car accessories, bike parts, automotive tools, and vehicle care products', 'AUTOMOTIVE', 9, true, TIMESTAMP '2026-01-17 10:35:00.000000', TIMESTAMP '2026-01-17 10:35:00.000000');

INSERT INTO category (category_id, section_id, name, description, code, display_order, enabled, created_at, updated_at)
VALUES ('d0e1f2a3-b4c5-4678-d901-234567890123', '768d4ee5-0ef7-458d-9748-409260cf93aa', 'Health & Wellness', 'Health supplements, vitamins, medical supplies, and wellness products', 'HEALTH_WELLNESS', 10, true, TIMESTAMP '2026-01-17 10:35:00.000000', TIMESTAMP '2026-01-17 10:35:00.000000');

INSERT INTO category (category_id, section_id, name, description, code, display_order, enabled, created_at, updated_at)
VALUES ('e1f2a3b4-c5d6-4789-e012-345678901234', '768d4ee5-0ef7-458d-9748-409260cf93aa', 'Pet Supplies', 'Pet food, toys, accessories, and care products for dogs, cats, and other pets', 'PET_SUPPLIES', 11, true, TIMESTAMP '2026-01-17 10:35:00.000000', TIMESTAMP '2026-01-17 10:35:00.000000');

INSERT INTO category (category_id, section_id, name, description, code, display_order, enabled, created_at, updated_at)
VALUES ('f2a3b4c5-d6e7-4890-f123-456789012345', '768d4ee5-0ef7-458d-9748-409260cf93aa', 'Baby Products', 'Baby food, diapers, clothing, toys, and care essentials for infants', 'BABY_PRODUCTS', 12, true, TIMESTAMP '2026-01-17 10:35:00.000000', TIMESTAMP '2026-01-17 10:35:00.000000');

INSERT INTO category (category_id, section_id, name, description, code, display_order, enabled, created_at, updated_at)
VALUES ('a3b4c5d6-e7f8-4901-a234-567890123456', '768d4ee5-0ef7-458d-9748-409260cf93aa', 'Jewelry & Watches', 'Gold, silver, diamond jewelry, watches, and luxury accessories', 'JEWELRY_WATCHES', 13, true, TIMESTAMP '2026-01-17 10:35:00.000000', TIMESTAMP '2026-01-17 10:35:00.000000');

INSERT INTO category (category_id, section_id, name, description, code, display_order, enabled, created_at, updated_at)
VALUES ('b4c5d6e7-f8a9-4012-b345-678901234567', '768d4ee5-0ef7-458d-9748-409260cf93aa', 'Garden & Outdoor', 'Plants, seeds, gardening tools, outdoor furniture, and patio accessories', 'GARDEN_OUTDOOR', 14, true, TIMESTAMP '2026-01-17 10:35:00.000000', TIMESTAMP '2026-01-17 10:35:00.000000');

INSERT INTO category (category_id, section_id, name, description, code, display_order, enabled, created_at, updated_at)
VALUES ('c5d6e7f8-a9b0-4123-c456-789012345678', '768d4ee5-0ef7-458d-9748-409260cf93aa', 'Office Supplies', 'Stationery, office furniture, printers, and business essentials', 'OFFICE_SUPPLIES', 15, true, TIMESTAMP '2026-01-17 10:35:00.000000', TIMESTAMP '2026-01-17 10:35:00.000000');

-- Disabled categories (for testing admin vs user API behavior)
INSERT INTO category (category_id, section_id, name, description, code, display_order, enabled, created_at, updated_at)
VALUES ('d6e7f8a9-b0c1-4234-d567-890123456789', '768d4ee5-0ef7-458d-9748-409260cf93aa', 'Luxury Items', 'Premium luxury products and exclusive collections', 'LUXURY_ITEMS', 16, false, TIMESTAMP '2026-01-17 10:35:00.000000', TIMESTAMP '2026-01-17 10:35:00.000000');

INSERT INTO category (category_id, section_id, name, description, code, display_order, enabled, created_at, updated_at)
VALUES ('e7f8a9b0-c1d2-4345-e678-901234567890', '768d4ee5-0ef7-458d-9748-409260cf93aa', 'Seasonal Items', 'Holiday decorations, seasonal products, and limited-time items', 'SEASONAL_ITEMS', 17, false, TIMESTAMP '2026-01-17 10:35:00.000000', TIMESTAMP '2026-01-17 10:35:00.000000');

INSERT INTO category (category_id, section_id, name, description, code, display_order, enabled, created_at, updated_at)
VALUES ('f8a9b0c1-d2e3-4456-f789-012345678901', '768d4ee5-0ef7-458d-9748-409260cf93aa', 'Art & Crafts', 'Art supplies, craft materials, DIY kits, and creative tools', 'ART_CRAFTS', 18, false, TIMESTAMP '2026-01-17 10:35:00.000000', TIMESTAMP '2026-01-17 10:35:00.000000');