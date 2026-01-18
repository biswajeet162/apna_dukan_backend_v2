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

-- Category Data
-- Categories for PRODUCT_CATEGORY section (section_id: 768d4ee5-0ef7-458d-9748-409260cf93aa)

INSERT INTO category (category_id, section_id, name, description, code, display_order, enabled, created_at, updated_at)
VALUES ('a1b2c3d4-e5f6-4789-a012-345678901234', '768d4ee5-0ef7-458d-9748-409260cf93aa', 'Grocery', 'Fresh fruits, vegetables, dairy products, and daily essentials', 'GROCERY', 1, true, TIMESTAMP '2026-01-17 10:35:00.000000', TIMESTAMP '2026-01-17 10:35:00.000000');

INSERT INTO category (category_id, section_id, name, description, code, display_order, enabled, created_at, updated_at)
VALUES ('b2c3d4e5-f6a7-4890-b123-456789012345', '768d4ee5-0ef7-458d-9748-409260cf93aa', 'Electronics', 'Mobile phones, laptops, gadgets, and electronic accessories', 'ELECTRONICS', 2, true, TIMESTAMP '2026-01-17 10:35:00.000000', TIMESTAMP '2026-01-17 10:35:00.000000');

INSERT INTO category (category_id, section_id, name, description, code, display_order, enabled, created_at, updated_at)
VALUES ('c3d4e5f6-a7b8-4901-c234-567890123456', '768d4ee5-0ef7-458d-9748-409260cf93aa', 'Fashion', 'Clothing, footwear, and fashion accessories for men and women', 'FASHION', 3, true, TIMESTAMP '2026-01-17 10:35:00.000000', TIMESTAMP '2026-01-17 10:35:00.000000');

INSERT INTO category (category_id, section_id, name, description, code, display_order, enabled, created_at, updated_at)
VALUES ('d4e5f6a7-b8c9-4012-d345-678901234567', '768d4ee5-0ef7-458d-9748-409260cf93aa', 'Home & Kitchen', 'Home decor, kitchen appliances, furniture, and household items', 'HOME_KITCHEN', 4, true, TIMESTAMP '2026-01-17 10:35:00.000000', TIMESTAMP '2026-01-17 10:35:00.000000');

INSERT INTO category (category_id, section_id, name, description, code, display_order, enabled, created_at, updated_at)
VALUES ('e5f6a7b8-c9d0-4123-e456-789012345678', '768d4ee5-0ef7-458d-9748-409260cf93aa', 'Beauty & Personal Care', 'Skincare, makeup, hair care, and personal hygiene products', 'BEAUTY_PERSONAL_CARE', 5, true, TIMESTAMP '2026-01-17 10:35:00.000000', TIMESTAMP '2026-01-17 10:35:00.000000');

INSERT INTO category (category_id, section_id, name, description, code, display_order, enabled, created_at, updated_at)
VALUES ('f6a7b8c9-d0e1-4234-f567-890123456789', '768d4ee5-0ef7-458d-9748-409260cf93aa', 'Sports & Outdoors', 'Sports equipment, outdoor gear, fitness accessories', 'SPORTS_OUTDOORS', 6, true, TIMESTAMP '2026-01-17 10:35:00.000000', TIMESTAMP '2026-01-17 10:35:00.000000');

INSERT INTO category (category_id, section_id, name, description, code, display_order, enabled, created_at, updated_at)
VALUES ('a7b8c9d0-e1f2-4345-a678-901234567890', '768d4ee5-0ef7-458d-9748-409260cf93aa', 'Books & Media', 'Books, e-books, movies, music, and educational content', 'BOOKS_MEDIA', 7, true, TIMESTAMP '2026-01-17 10:35:00.000000', TIMESTAMP '2026-01-17 10:35:00.000000');

INSERT INTO category (category_id, section_id, name, description, code, display_order, enabled, created_at, updated_at)
VALUES ('b8c9d0e1-f2a3-4456-b789-012345678901', '768d4ee5-0ef7-458d-9748-409260cf93aa', 'Toys & Games', 'Toys, board games, puzzles, and children entertainment', 'TOYS_GAMES', 8, true, TIMESTAMP '2026-01-17 10:35:00.000000', TIMESTAMP '2026-01-17 10:35:00.000000');

INSERT INTO category (category_id, section_id, name, description, code, display_order, enabled, created_at, updated_at)
VALUES ('c9d0e1f2-a3b4-4567-c890-123456789012', '768d4ee5-0ef7-458d-9748-409260cf93aa', 'Automotive', 'Car accessories, tools, and automotive supplies', 'AUTOMOTIVE', 9, true, TIMESTAMP '2026-01-17 10:35:00.000000', TIMESTAMP '2026-01-17 10:35:00.000000');

INSERT INTO category (category_id, section_id, name, description, code, display_order, enabled, created_at, updated_at)
VALUES ('d0e1f2a3-b4c5-4678-d901-234567890123', '768d4ee5-0ef7-458d-9748-409260cf93aa', 'Health & Wellness', 'Vitamins, supplements, health monitors, and wellness products', 'HEALTH_WELLNESS', 10, true, TIMESTAMP '2026-01-17 10:35:00.000000', TIMESTAMP '2026-01-17 10:35:00.000000');

INSERT INTO category (category_id, section_id, name, description, code, display_order, enabled, created_at, updated_at)
VALUES ('e1f2a3b4-c5d6-4789-e012-345678901234', '768d4ee5-0ef7-458d-9748-409260cf93aa', 'Pet Supplies', 'Pet food, toys, accessories, and care products', 'PET_SUPPLIES', 11, true, TIMESTAMP '2026-01-17 10:35:00.000000', TIMESTAMP '2026-01-17 10:35:00.000000');

INSERT INTO category (category_id, section_id, name, description, code, display_order, enabled, created_at, updated_at)
VALUES ('f2a3b4c5-d6e7-4890-f123-456789012345', '768d4ee5-0ef7-458d-9748-409260cf93aa', 'Office Supplies', 'Stationery, office furniture, and workplace essentials', 'OFFICE_SUPPLIES', 12, true, TIMESTAMP '2026-01-17 10:35:00.000000', TIMESTAMP '2026-01-17 10:35:00.000000');

INSERT INTO category (category_id, section_id, name, description, code, display_order, enabled, created_at, updated_at)
VALUES ('a3b4c5d6-e7f8-4901-a234-567890123456', '768d4ee5-0ef7-458d-9748-409260cf93aa', 'Jewelry & Watches', 'Rings, necklaces, watches, and fine jewelry', 'JEWELRY_WATCHES', 13, true, TIMESTAMP '2026-01-17 10:35:00.000000', TIMESTAMP '2026-01-17 10:35:00.000000');

INSERT INTO category (category_id, section_id, name, description, code, display_order, enabled, created_at, updated_at)
VALUES ('b4c5d6e7-f8a9-4012-b345-678901234567', '768d4ee5-0ef7-458d-9748-409260cf93aa', 'Baby Products', 'Baby food, clothing, toys, and care essentials', 'BABY_PRODUCTS', 14, true, TIMESTAMP '2026-01-17 10:35:00.000000', TIMESTAMP '2026-01-17 10:35:00.000000');

INSERT INTO category (category_id, section_id, name, description, code, display_order, enabled, created_at, updated_at)
VALUES ('c5d6e7f8-a9b0-4123-c456-789012345678', '768d4ee5-0ef7-458d-9748-409260cf93aa', 'Garden & Outdoor', 'Plants, seeds, garden tools, and outdoor furniture', 'GARDEN_OUTDOOR', 15, true, TIMESTAMP '2026-01-17 10:35:00.000000', TIMESTAMP '2026-01-17 10:35:00.000000');

INSERT INTO category (category_id, section_id, name, description, code, display_order, enabled, created_at, updated_at)
VALUES ('d6e7f8a9-b0c1-4234-d567-890123456789', '768d4ee5-0ef7-458d-9748-409260cf93aa', 'Musical Instruments', 'Guitars, pianos, drums, and music equipment', 'MUSICAL_INSTRUMENTS', 16, true, TIMESTAMP '2026-01-17 10:35:00.000000', TIMESTAMP '2026-01-17 10:35:00.000000');

INSERT INTO category (category_id, section_id, name, description, code, display_order, enabled, created_at, updated_at)
VALUES ('e7f8a9b0-c1d2-4345-e678-901234567890', '768d4ee5-0ef7-458d-9748-409260cf93aa', 'Travel & Luggage', 'Suitcases, backpacks, travel accessories', 'TRAVEL_LUGGAGE', 17, true, TIMESTAMP '2026-01-17 10:35:00.000000', TIMESTAMP '2026-01-17 10:35:00.000000');

INSERT INTO category (category_id, section_id, name, description, code, display_order, enabled, created_at, updated_at)
VALUES ('f8a9b0c1-d2e3-4456-f789-012345678901', '768d4ee5-0ef7-458d-9748-409260cf93aa', 'Art & Crafts', 'Art supplies, craft materials, DIY kits, and creative tools', 'ART_CRAFTS', 18, false, TIMESTAMP '2026-01-17 10:35:00.000000', TIMESTAMP '2026-01-17 10:35:00.000000');

-- SubCategory Data
-- SubCategories for Grocery (category_id: a1b2c3d4-e5f6-4789-a012-345678901234)

INSERT INTO sub_category (sub_category_id, category_id, name, description, code, display_order, enabled, image_urls, created_at, updated_at)
VALUES ('f1a2b3c4-d5e6-4789-f012-345678901234', 'a1b2c3d4-e5f6-4789-a012-345678901234', 'Fruits & Vegetables', 'Fresh fruits and vegetables', 'FRUITS_VEGETABLES', 1, true, '[]', TIMESTAMP '2026-01-17 10:40:00.000000', TIMESTAMP '2026-01-17 10:40:00.000000');

INSERT INTO sub_category (sub_category_id, category_id, name, description, code, display_order, enabled, image_urls, created_at, updated_at)
VALUES ('a2b3c4d5-e6f7-4890-a123-456789012345', 'a1b2c3d4-e5f6-4789-a012-345678901234', 'Dairy Products', 'Milk, cheese, yogurt and other dairy items', 'DAIRY_PRODUCTS', 2, true, '[]', TIMESTAMP '2026-01-17 10:40:00.000000', TIMESTAMP '2026-01-17 10:40:00.000000');

INSERT INTO sub_category (sub_category_id, category_id, name, description, code, display_order, enabled, image_urls, created_at, updated_at)
VALUES ('b3c4d5e6-f7a8-4901-b234-567890123456', 'a1b2c3d4-e5f6-4789-a012-345678901234', 'Frozen Foods', 'Frozen food items', 'FROZEN_FOODS', 3, false, '[]', TIMESTAMP '2026-01-17 10:40:00.000000', TIMESTAMP '2026-01-17 10:40:00.000000');

INSERT INTO sub_category (sub_category_id, category_id, name, description, code, display_order, enabled, image_urls, created_at, updated_at)
VALUES ('c4d5e6f7-a8b9-4012-c345-678901234567', 'a1b2c3d4-e5f6-4789-a012-345678901234', 'Beverages', 'Soft drinks, juices, and beverages', 'BEVERAGES', 4, true, '[]', TIMESTAMP '2026-01-17 10:40:00.000000', TIMESTAMP '2026-01-17 10:40:00.000000');

INSERT INTO sub_category (sub_category_id, category_id, name, description, code, display_order, enabled, image_urls, created_at, updated_at)
VALUES ('d5e6f7a8-b9c0-4123-d456-789012345678', 'a1b2c3d4-e5f6-4789-a012-345678901234', 'Snacks & Sweets', 'Chips, cookies, chocolates and snacks', 'SNACKS_SWEETS', 5, true, '[]', TIMESTAMP '2026-01-17 10:40:00.000000', TIMESTAMP '2026-01-17 10:40:00.000000');

-- SubCategories for Electronics (category_id: b2c3d4e5-f6a7-4890-b123-456789012345)

INSERT INTO sub_category (sub_category_id, category_id, name, description, code, display_order, enabled, image_urls, created_at, updated_at)
VALUES ('e6f7a8b9-c0d1-4234-e567-890123456789', 'b2c3d4e5-f6a7-4890-b123-456789012345', 'Mobile Phones', 'Smartphones and mobile accessories', 'MOBILE_PHONES', 1, true, '[]', TIMESTAMP '2026-01-17 10:40:00.000000', TIMESTAMP '2026-01-17 10:40:00.000000');

INSERT INTO sub_category (sub_category_id, category_id, name, description, code, display_order, enabled, image_urls, created_at, updated_at)
VALUES ('f7a8b9c0-d1e2-4345-f678-901234567890', 'b2c3d4e5-f6a7-4890-b123-456789012345', 'Laptops & Computers', 'Laptops, desktops, and computer accessories', 'LAPTOPS_COMPUTERS', 2, true, '[]', TIMESTAMP '2026-01-17 10:40:00.000000', TIMESTAMP '2026-01-17 10:40:00.000000');

INSERT INTO sub_category (sub_category_id, category_id, name, description, code, display_order, enabled, image_urls, created_at, updated_at)
VALUES ('a8b9c0d1-e2f3-4456-a789-012345678901', 'b2c3d4e5-f6a7-4890-b123-456789012345', 'Audio & Headphones', 'Speakers, headphones, and audio devices', 'AUDIO_HEADPHONES', 3, true, '[]', TIMESTAMP '2026-01-17 10:40:00.000000', TIMESTAMP '2026-01-17 10:40:00.000000');

INSERT INTO sub_category (sub_category_id, category_id, name, description, code, display_order, enabled, image_urls, created_at, updated_at)
VALUES ('b9c0d1e2-f3a4-4567-b890-123456789012', 'b2c3d4e5-f6a7-4890-b123-456789012345', 'Cameras & Photography', 'Digital cameras, lenses, and photography equipment', 'CAMERAS_PHOTOGRAPHY', 4, true, '[]', TIMESTAMP '2026-01-17 10:40:00.000000', TIMESTAMP '2026-01-17 10:40:00.000000');

INSERT INTO sub_category (sub_category_id, category_id, name, description, code, display_order, enabled, image_urls, created_at, updated_at)
VALUES ('c0d1e2f3-a4b5-4678-c901-234567890123', 'b2c3d4e5-f6a7-4890-b123-456789012345', 'Gaming Consoles', 'Gaming consoles and gaming accessories', 'GAMING_CONSOLES', 5, false, '[]', TIMESTAMP '2026-01-17 10:40:00.000000', TIMESTAMP '2026-01-17 10:40:00.000000');

-- SubCategories for Fashion (category_id: c3d4e5f6-a7b8-4901-c234-567890123456)

INSERT INTO sub_category (sub_category_id, category_id, name, description, code, display_order, enabled, image_urls, created_at, updated_at)
VALUES ('d1e2f3a4-b5c6-4789-d012-345678901234', 'c3d4e5f6-a7b8-4901-c234-567890123456', 'Men''s Clothing', 'Shirts, pants, t-shirts for men', 'MENS_CLOTHING', 1, true, '[]', TIMESTAMP '2026-01-17 10:40:00.000000', TIMESTAMP '2026-01-17 10:40:00.000000');

INSERT INTO sub_category (sub_category_id, category_id, name, description, code, display_order, enabled, image_urls, created_at, updated_at)
VALUES ('e2f3a4b5-c6d7-4890-e123-456789012345', 'c3d4e5f6-a7b8-4901-c234-567890123456', 'Women''s Clothing', 'Dresses, tops, bottoms for women', 'WOMENS_CLOTHING', 2, true, '[]', TIMESTAMP '2026-01-17 10:40:00.000000', TIMESTAMP '2026-01-17 10:40:00.000000');

INSERT INTO sub_category (sub_category_id, category_id, name, description, code, display_order, enabled, image_urls, created_at, updated_at)
VALUES ('f3a4b5c6-d7e8-4901-f234-567890123456', 'c3d4e5f6-a7b8-4901-c234-567890123456', 'Footwear', 'Shoes, sandals, and footwear for all', 'FOOTWEAR', 3, true, '[]', TIMESTAMP '2026-01-17 10:40:00.000000', TIMESTAMP '2026-01-17 10:40:00.000000');

INSERT INTO sub_category (sub_category_id, category_id, name, description, code, display_order, enabled, image_urls, created_at, updated_at)
VALUES ('a4b5c6d7-e8f9-4012-a345-678901234567', 'c3d4e5f6-a7b8-4901-c234-567890123456', 'Accessories', 'Bags, wallets, belts, and fashion accessories', 'ACCESSORIES', 4, true, '[]', TIMESTAMP '2026-01-17 10:40:00.000000', TIMESTAMP '2026-01-17 10:40:00.000000');

-- SubCategories for Home & Kitchen (category_id: d4e5f6a7-b8c9-4012-d345-678901234567)

INSERT INTO sub_category (sub_category_id, category_id, name, description, code, display_order, enabled, image_urls, created_at, updated_at)
VALUES ('b5c6d7e8-f9a0-4123-b456-789012345678', 'd4e5f6a7-b8c9-4012-d345-678901234567', 'Kitchen Appliances', 'Mixers, blenders, and kitchen gadgets', 'KITCHEN_APPLIANCES', 1, true, '[]', TIMESTAMP '2026-01-17 10:40:00.000000', TIMESTAMP '2026-01-17 10:40:00.000000');

INSERT INTO sub_category (sub_category_id, category_id, name, description, code, display_order, enabled, image_urls, created_at, updated_at)
VALUES ('c6d7e8f9-a0b1-4234-c567-890123456789', 'd4e5f6a7-b8c9-4012-d345-678901234567', 'Home Decor', 'Wall art, vases, and decorative items', 'HOME_DECOR', 2, true, '[]', TIMESTAMP '2026-01-17 10:40:00.000000', TIMESTAMP '2026-01-17 10:40:00.000000');

INSERT INTO sub_category (sub_category_id, category_id, name, description, code, display_order, enabled, image_urls, created_at, updated_at)
VALUES ('d7e8f9a0-b1c2-4345-d678-901234567890', 'd4e5f6a7-b8c9-4012-d345-678901234567', 'Furniture', 'Chairs, tables, and home furniture', 'FURNITURE', 3, true, '[]', TIMESTAMP '2026-01-17 10:40:00.000000', TIMESTAMP '2026-01-17 10:40:00.000000');

INSERT INTO sub_category (sub_category_id, category_id, name, description, code, display_order, enabled, image_urls, created_at, updated_at)
VALUES ('e8f9a0b1-c2d3-4456-e789-012345678901', 'd4e5f6a7-b8c9-4012-d345-678901234567', 'Bedding & Bath', 'Bed sheets, towels, and bath accessories', 'BEDDING_BATH', 4, false, '[]', TIMESTAMP '2026-01-17 10:40:00.000000', TIMESTAMP '2026-01-17 10:40:00.000000');

-- SubCategories for Beauty & Personal Care (category_id: e5f6a7b8-c9d0-4123-e456-789012345678)

INSERT INTO sub_category (sub_category_id, category_id, name, description, code, display_order, enabled, image_urls, created_at, updated_at)
VALUES ('f9a0b1c2-d3e4-4567-f890-123456789012', 'e5f6a7b8-c9d0-4123-e456-789012345678', 'Skincare', 'Face creams, serums, and skincare products', 'SKINCARE', 1, true, '[]', TIMESTAMP '2026-01-17 10:40:00.000000', TIMESTAMP '2026-01-17 10:40:00.000000');

INSERT INTO sub_category (sub_category_id, category_id, name, description, code, display_order, enabled, image_urls, created_at, updated_at)
VALUES ('a0b1c2d3-e4f5-4678-a901-234567890123', 'e5f6a7b8-c9d0-4123-e456-789012345678', 'Makeup', 'Lipstick, foundation, and makeup products', 'MAKEUP', 2, true, '[]', TIMESTAMP '2026-01-17 10:40:00.000000', TIMESTAMP '2026-01-17 10:40:00.000000');

INSERT INTO sub_category (sub_category_id, category_id, name, description, code, display_order, enabled, image_urls, created_at, updated_at)
VALUES ('b1c2d3e4-f5a6-4789-b012-345678901234', 'e5f6a7b8-c9d0-4123-e456-789012345678', 'Hair Care', 'Shampoo, conditioner, and hair products', 'HAIR_CARE', 3, true, '[]', TIMESTAMP '2026-01-17 10:40:00.000000', TIMESTAMP '2026-01-17 10:40:00.000000');

INSERT INTO sub_category (sub_category_id, category_id, name, description, code, display_order, enabled, image_urls, created_at, updated_at)
VALUES ('c2d3e4f5-a6b7-4890-c123-456789012345', 'e5f6a7b8-c9d0-4123-e456-789012345678', 'Personal Hygiene', 'Soaps, deodorants, and hygiene products', 'PERSONAL_HYGIENE', 4, true, '[]', TIMESTAMP '2026-01-17 10:40:00.000000', TIMESTAMP '2026-01-17 10:40:00.000000');

-- Product Group Data
-- Product Groups for Fruits & Vegetables (sub_category_id: f1a2b3c4-d5e6-4789-f012-345678901234)

INSERT INTO product_group (product_group_id, sub_category_id, name, description, code, display_order, enabled, image_urls, created_at, updated_at)
VALUES (CAST('a1a2b3c4-d5e6-4789-a012-345678901234' AS UUID), CAST('f1a2b3c4-d5e6-4789-f012-345678901234' AS UUID), 'Fresh Fruits', 'Fresh seasonal fruits', 'FRESH_FRUITS', 1, true, '["https://example.com/fruits1.jpg", "https://example.com/fruits2.jpg"]', TIMESTAMP '2026-01-17 10:45:00.000000', TIMESTAMP '2026-01-17 10:45:00.000000');

INSERT INTO product_group (product_group_id, sub_category_id, name, description, code, display_order, enabled, image_urls, created_at, updated_at)
VALUES (CAST('a2b3c4d5-e6f7-4890-a123-456789012345' AS UUID), CAST('f1a2b3c4-d5e6-4789-f012-345678901234' AS UUID), 'Fresh Vegetables', 'Fresh seasonal vegetables', 'FRESH_VEGETABLES', 2, true, '["https://example.com/vegetables1.jpg"]', TIMESTAMP '2026-01-17 10:45:00.000000', TIMESTAMP '2026-01-17 10:45:00.000000');

INSERT INTO product_group (product_group_id, sub_category_id, name, description, code, display_order, enabled, image_urls, created_at, updated_at)
VALUES (CAST('a3c4d5e6-f7a8-4901-a234-567890123456' AS UUID), CAST('f1a2b3c4-d5e6-4789-f012-345678901234' AS UUID), 'Organic Produce', 'Organic fruits and vegetables', 'ORGANIC_PRODUCE', 3, false, '[]', TIMESTAMP '2026-01-17 10:45:00.000000', TIMESTAMP '2026-01-17 10:45:00.000000');

-- Product Groups for Dairy Products (sub_category_id: a2b3c4d5-e6f7-4890-a123-456789012345)

INSERT INTO product_group (product_group_id, sub_category_id, name, description, code, display_order, enabled, image_urls, created_at, updated_at)
VALUES (CAST('a4d5e6f7-a8b9-4012-a345-678901234567' AS UUID), CAST('a2b3c4d5-e6f7-4890-a123-456789012345' AS UUID), 'Milk & Cream', 'Fresh milk and cream products', 'MILK_CREAM', 1, true, '["https://example.com/milk.jpg"]', TIMESTAMP '2026-01-17 10:45:00.000000', TIMESTAMP '2026-01-17 10:45:00.000000');

INSERT INTO product_group (product_group_id, sub_category_id, name, description, code, display_order, enabled, image_urls, created_at, updated_at)
VALUES (CAST('a5e6f7a8-b9c0-4123-a456-789012345678' AS UUID), CAST('a2b3c4d5-e6f7-4890-a123-456789012345' AS UUID), 'Cheese & Butter', 'Various cheese and butter products', 'CHEESE_BUTTER', 2, true, '["https://example.com/cheese1.jpg", "https://example.com/cheese2.jpg"]', TIMESTAMP '2026-01-17 10:45:00.000000', TIMESTAMP '2026-01-17 10:45:00.000000');

INSERT INTO product_group (product_group_id, sub_category_id, name, description, code, display_order, enabled, image_urls, created_at, updated_at)
VALUES (CAST('a6f7a8b9-c0d1-4234-a567-890123456789' AS UUID), CAST('a2b3c4d5-e6f7-4890-a123-456789012345' AS UUID), 'Yogurt & Curd', 'Yogurt and curd products', 'YOGURT_CURD', 3, true, '[]', TIMESTAMP '2026-01-17 10:45:00.000000', TIMESTAMP '2026-01-17 10:45:00.000000');

-- Product Groups for Mobile Phones (sub_category_id: e6f7a8b9-c0d1-4234-e567-890123456789)

INSERT INTO product_group (product_group_id, sub_category_id, name, description, code, display_order, enabled, image_urls, created_at, updated_at)
VALUES (CAST('a7a8b9c0-d1e2-4345-a678-901234567890' AS UUID), CAST('e6f7a8b9-c0d1-4234-e567-890123456789' AS UUID), 'Smartphones', 'Latest smartphones from top brands', 'SMARTPHONES', 1, true, '["https://example.com/smartphones1.jpg", "https://example.com/smartphones2.jpg"]', TIMESTAMP '2026-01-17 10:45:00.000000', TIMESTAMP '2026-01-17 10:45:00.000000');

INSERT INTO product_group (product_group_id, sub_category_id, name, description, code, display_order, enabled, image_urls, created_at, updated_at)
VALUES (CAST('a8b9c0d1-e2f3-4456-a789-012345678901' AS UUID), CAST('e6f7a8b9-c0d1-4234-e567-890123456789' AS UUID), 'Budget Phones', 'Affordable smartphones', 'BUDGET_PHONES', 2, true, '["https://example.com/budget.jpg"]', TIMESTAMP '2026-01-17 10:45:00.000000', TIMESTAMP '2026-01-17 10:45:00.000000');

INSERT INTO product_group (product_group_id, sub_category_id, name, description, code, display_order, enabled, image_urls, created_at, updated_at)
VALUES (CAST('a9c0d1e2-f3a4-4567-a890-123456789012' AS UUID), CAST('e6f7a8b9-c0d1-4234-e567-890123456789' AS UUID), 'Premium Phones', 'Flagship and premium smartphones', 'PREMIUM_PHONES', 3, true, '[]', TIMESTAMP '2026-01-17 10:45:00.000000', TIMESTAMP '2026-01-17 10:45:00.000000');

-- Product Groups for Men's Clothing (sub_category_id: d1e2f3a4-b5c6-4789-d012-345678901234)

INSERT INTO product_group (product_group_id, sub_category_id, name, description, code, display_order, enabled, image_urls, created_at, updated_at)
VALUES (CAST('a0d1e2f3-a4b5-4678-a901-234567890123' AS UUID), CAST('d1e2f3a4-b5c6-4789-d012-345678901234' AS UUID), 'Casual Wear', 'Casual shirts, t-shirts, and pants', 'CASUAL_WEAR', 1, true, '["https://example.com/casual1.jpg"]', TIMESTAMP '2026-01-17 10:45:00.000000', TIMESTAMP '2026-01-17 10:45:00.000000');

INSERT INTO product_group (product_group_id, sub_category_id, name, description, code, display_order, enabled, image_urls, created_at, updated_at)
VALUES (CAST('b1e2f3a4-b5c6-4789-a012-345678901234' AS UUID), CAST('d1e2f3a4-b5c6-4789-d012-345678901234' AS UUID), 'Formal Wear', 'Formal shirts, suits, and trousers', 'FORMAL_WEAR', 2, true, '["https://example.com/formal1.jpg", "https://example.com/formal2.jpg"]', TIMESTAMP '2026-01-17 10:45:00.000000', TIMESTAMP '2026-01-17 10:45:00.000000');

INSERT INTO product_group (product_group_id, sub_category_id, name, description, code, display_order, enabled, image_urls, created_at, updated_at)
VALUES (CAST('c2f3a4b5-c6d7-4890-a123-456789012345' AS UUID), CAST('d1e2f3a4-b5c6-4789-d012-345678901234' AS UUID), 'Sports Wear', 'Activewear and sports clothing', 'SPORTS_WEAR', 3, false, '[]', TIMESTAMP '2026-01-17 10:45:00.000000', TIMESTAMP '2026-01-17 10:45:00.000000');

-- Product Groups for Skincare (sub_category_id: f9a0b1c2-d3e4-4567-f890-123456789012)

INSERT INTO product_group (product_group_id, sub_category_id, name, description, code, display_order, enabled, image_urls, created_at, updated_at)
VALUES (CAST('d3a4b5c6-d7e8-4901-a234-567890123456' AS UUID), CAST('f9a0b1c2-d3e4-4567-f890-123456789012' AS UUID), 'Face Care', 'Face creams, cleansers, and toners', 'FACE_CARE', 1, true, '["https://example.com/facecare1.jpg"]', TIMESTAMP '2026-01-17 10:45:00.000000', TIMESTAMP '2026-01-17 10:45:00.000000');

INSERT INTO product_group (product_group_id, sub_category_id, name, description, code, display_order, enabled, image_urls, created_at, updated_at)
VALUES (CAST('e4b5c6d7-e8f9-4012-a345-678901234567' AS UUID), CAST('f9a0b1c2-d3e4-4567-f890-123456789012' AS UUID), 'Body Care', 'Body lotions, moisturizers, and scrubs', 'BODY_CARE', 2, true, '["https://example.com/bodycare1.jpg", "https://example.com/bodycare2.jpg"]', TIMESTAMP '2026-01-17 10:45:00.000000', TIMESTAMP '2026-01-17 10:45:00.000000');

INSERT INTO product_group (product_group_id, sub_category_id, name, description, code, display_order, enabled, image_urls, created_at, updated_at)
VALUES (CAST('f5c6d7e8-f9a0-4123-a456-789012345678' AS UUID), CAST('f9a0b1c2-d3e4-4567-f890-123456789012' AS UUID), 'Anti-Aging', 'Anti-aging serums and creams', 'ANTI_AGING', 3, true, '[]', TIMESTAMP '2026-01-17 10:45:00.000000', TIMESTAMP '2026-01-17 10:45:00.000000');

-- Product Data
-- Product for Fresh Fruits (product_group_id: a1a2b3c4-d5e6-4789-a012-345678901234)

INSERT INTO product (product_id, product_group_id, name, brand, code, primary_image_url, display_order, enabled, created_at, updated_at)
VALUES (CAST('f1a2b3c4-d5e6-4789-f012-345678901234' AS UUID), CAST('a1a2b3c4-d5e6-4789-a012-345678901234' AS UUID), 'Apple - Red Delicious', 'Fresh Farm', 'APPLE_RED_DELICIOUS', 'https://example.com/apple.jpg', 1, true, TIMESTAMP '2026-01-17 10:50:00.000000', TIMESTAMP '2026-01-17 10:50:00.000000');

-- Variant Data
-- Variants for Apple - Red Delicious (product_id: f1a2b3c4-d5e6-4789-f012-345678901234)

INSERT INTO variant (variant_id, product_id, label, enabled, is_default, display_order, created_at, updated_at)
VALUES (CAST('b1a2b3c4-d5e6-4789-b012-345678901234' AS UUID), CAST('f1a2b3c4-d5e6-4789-f012-345678901234' AS UUID), '1 kg', true, true, 1, TIMESTAMP '2026-01-17 10:50:00.000000', TIMESTAMP '2026-01-17 10:50:00.000000');

INSERT INTO variant (variant_id, product_id, label, enabled, is_default, display_order, created_at, updated_at)
VALUES (CAST('b2a2b3c4-d5e6-4789-b012-345678901234' AS UUID), CAST('f1a2b3c4-d5e6-4789-f012-345678901234' AS UUID), '2 kg', true, false, 2, TIMESTAMP '2026-01-17 10:50:00.000000', TIMESTAMP '2026-01-17 10:50:00.000000');

INSERT INTO variant (variant_id, product_id, label, enabled, is_default, display_order, created_at, updated_at)
VALUES (CAST('b3a2b3c4-d5e6-4789-b012-345678901234' AS UUID), CAST('f1a2b3c4-d5e6-4789-f012-345678901234' AS UUID), '5 kg', true, false, 3, TIMESTAMP '2026-01-17 10:50:00.000000', TIMESTAMP '2026-01-17 10:50:00.000000');

-- Pricing Data
-- Pricing for variants of Apple - Red Delicious

INSERT INTO pricing (pricing_id, variant_id, selling_price, mrp, discount_percent, currency, active, created_at, updated_at)
VALUES (CAST('c1a2b3c4-d5e6-4789-c012-345678901234' AS UUID), CAST('b1a2b3c4-d5e6-4789-b012-345678901234' AS UUID), 80.0, 100.0, 20, 'INR', true, TIMESTAMP '2026-01-17 10:50:00.000000', TIMESTAMP '2026-01-17 10:50:00.000000');

INSERT INTO pricing (pricing_id, variant_id, selling_price, mrp, discount_percent, currency, active, created_at, updated_at)
VALUES (CAST('c2a2b3c4-d5e6-4789-c012-345678901234' AS UUID), CAST('b2a2b3c4-d5e6-4789-b012-345678901234' AS UUID), 150.0, 180.0, 17, 'INR', true, TIMESTAMP '2026-01-17 10:50:00.000000', TIMESTAMP '2026-01-17 10:50:00.000000');

INSERT INTO pricing (pricing_id, variant_id, selling_price, mrp, discount_percent, currency, active, created_at, updated_at)
VALUES (CAST('c3a2b3c4-d5e6-4789-c012-345678901234' AS UUID), CAST('b3a2b3c4-d5e6-4789-b012-345678901234' AS UUID), 350.0, 400.0, 13, 'INR', true, TIMESTAMP '2026-01-17 10:50:00.000000', TIMESTAMP '2026-01-17 10:50:00.000000');

-- Inventory Data
-- Inventory for variants of Apple - Red Delicious

INSERT INTO inventory (inventory_id, variant_id, in_stock, created_at, updated_at)
VALUES (CAST('d1a2b3c4-d5e6-4789-d012-345678901234' AS UUID), CAST('b1a2b3c4-d5e6-4789-b012-345678901234' AS UUID), true, TIMESTAMP '2026-01-17 10:50:00.000000', TIMESTAMP '2026-01-17 10:50:00.000000');

INSERT INTO inventory (inventory_id, variant_id, in_stock, created_at, updated_at)
VALUES (CAST('d2a2b3c4-d5e6-4789-d012-345678901234' AS UUID), CAST('b2a2b3c4-d5e6-4789-b012-345678901234' AS UUID), true, TIMESTAMP '2026-01-17 10:50:00.000000', TIMESTAMP '2026-01-17 10:50:00.000000');

INSERT INTO inventory (inventory_id, variant_id, in_stock, created_at, updated_at)
VALUES (CAST('d3a2b3c4-d5e6-4789-d012-345678901234' AS UUID), CAST('b3a2b3c4-d5e6-4789-b012-345678901234' AS UUID), false, TIMESTAMP '2026-01-17 10:50:00.000000', TIMESTAMP '2026-01-17 10:50:00.000000');

-- Additional Product Data for PDP Testing
-- Product for Fresh Fruits (product_group_id: a1a2b3c4-d5e6-4789-a012-345678901234)

INSERT INTO product (product_id, product_group_id, name, brand, code, primary_image_url, display_order, enabled, created_at, updated_at)
VALUES (CAST('f2a2b3c4-d5e6-4789-f012-345678901234' AS UUID), CAST('a1a2b3c4-d5e6-4789-a012-345678901234' AS UUID), 'Banana - Cavendish', 'Tropical Fresh', 'BANANA_CAVENDISH', 'https://example.com/banana.jpg', 2, true, TIMESTAMP '2026-01-17 10:50:00.000000', TIMESTAMP '2026-01-17 10:50:00.000000');

-- Variants for Banana - Cavendish (product_id: f2a2b3c4-d5e6-4789-f012-345678901234)

INSERT INTO variant (variant_id, product_id, label, enabled, is_default, display_order, created_at, updated_at)
VALUES (CAST('b4a2b3c4-d5e6-4789-b012-345678901234' AS UUID), CAST('f2a2b3c4-d5e6-4789-f012-345678901234' AS UUID), '500 g', true, true, 1, TIMESTAMP '2026-01-17 10:50:00.000000', TIMESTAMP '2026-01-17 10:50:00.000000');

INSERT INTO variant (variant_id, product_id, label, enabled, is_default, display_order, created_at, updated_at)
VALUES (CAST('b5a2b3c4-d5e6-4789-b012-345678901234' AS UUID), CAST('f2a2b3c4-d5e6-4789-f012-345678901234' AS UUID), '1 kg', true, false, 2, TIMESTAMP '2026-01-17 10:50:00.000000', TIMESTAMP '2026-01-17 10:50:00.000000');

-- Pricing for Banana variants

INSERT INTO pricing (pricing_id, variant_id, selling_price, mrp, discount_percent, currency, active, created_at, updated_at)
VALUES (CAST('c4a2b3c4-d5e6-4789-c012-345678901234' AS UUID), CAST('b4a2b3c4-d5e6-4789-b012-345678901234' AS UUID), 25.0, 30.0, 17, 'INR', true, TIMESTAMP '2026-01-17 10:50:00.000000', TIMESTAMP '2026-01-17 10:50:00.000000');

INSERT INTO pricing (pricing_id, variant_id, selling_price, mrp, discount_percent, currency, active, created_at, updated_at)
VALUES (CAST('c5a2b3c4-d5e6-4789-c012-345678901234' AS UUID), CAST('b5a2b3c4-d5e6-4789-b012-345678901234' AS UUID), 45.0, 55.0, 18, 'INR', true, TIMESTAMP '2026-01-17 10:50:00.000000', TIMESTAMP '2026-01-17 10:50:00.000000');

-- Inventory for Banana variants

INSERT INTO inventory (inventory_id, variant_id, in_stock, created_at, updated_at)
VALUES (CAST('d4a2b3c4-d5e6-4789-d012-345678901234' AS UUID), CAST('b4a2b3c4-d5e6-4789-b012-345678901234' AS UUID), true, TIMESTAMP '2026-01-17 10:50:00.000000', TIMESTAMP '2026-01-17 10:50:00.000000');

INSERT INTO inventory (inventory_id, variant_id, in_stock, created_at, updated_at)
VALUES (CAST('d5a2b3c4-d5e6-4789-d012-345678901234' AS UUID), CAST('b5a2b3c4-d5e6-4789-b012-345678901234' AS UUID), true, TIMESTAMP '2026-01-17 10:50:00.000000', TIMESTAMP '2026-01-17 10:50:00.000000');

-- Product 3: Smartphone (Electronics - Mobile Phones)
-- Product for Smartphones (product_group_id: a7a8b9c0-d1e2-4345-a678-901234567890)

INSERT INTO product (product_id, product_group_id, name, brand, code, primary_image_url, display_order, enabled, created_at, updated_at)
VALUES (CAST('f3a2b3c4-d5e6-4789-f012-345678901234' AS UUID), CAST('a7a8b9c0-d1e2-4345-a678-901234567890' AS UUID), 'SmartPhone Pro Max', 'TechBrand', 'SMARTPHONE_PRO_MAX', 'https://example.com/smartphone.jpg', 1, true, TIMESTAMP '2026-01-17 10:50:00.000000', TIMESTAMP '2026-01-17 10:50:00.000000');

-- Variants for SmartPhone Pro Max (product_id: f3a2b3c4-d5e6-4789-f012-345678901234)

INSERT INTO variant (variant_id, product_id, label, enabled, is_default, display_order, created_at, updated_at)
VALUES (CAST('b6a2b3c4-d5e6-4789-b012-345678901234' AS UUID), CAST('f3a2b3c4-d5e6-4789-f012-345678901234' AS UUID), '128GB - Black', true, true, 1, TIMESTAMP '2026-01-17 10:50:00.000000', TIMESTAMP '2026-01-17 10:50:00.000000');

INSERT INTO variant (variant_id, product_id, label, enabled, is_default, display_order, created_at, updated_at)
VALUES (CAST('b7a2b3c4-d5e6-4789-b012-345678901234' AS UUID), CAST('f3a2b3c4-d5e6-4789-f012-345678901234' AS UUID), '256GB - Black', true, false, 2, TIMESTAMP '2026-01-17 10:50:00.000000', TIMESTAMP '2026-01-17 10:50:00.000000');

INSERT INTO variant (variant_id, product_id, label, enabled, is_default, display_order, created_at, updated_at)
VALUES (CAST('b8a2b3c4-d5e6-4789-b012-345678901234' AS UUID), CAST('f3a2b3c4-d5e6-4789-f012-345678901234' AS UUID), '128GB - Blue', true, false, 3, TIMESTAMP '2026-01-17 10:50:00.000000', TIMESTAMP '2026-01-17 10:50:00.000000');

INSERT INTO variant (variant_id, product_id, label, enabled, is_default, display_order, created_at, updated_at)
VALUES (CAST('b9a2b3c4-d5e6-4789-b012-345678901234' AS UUID), CAST('f3a2b3c4-d5e6-4789-f012-345678901234' AS UUID), '256GB - Blue', true, false, 4, TIMESTAMP '2026-01-17 10:50:00.000000', TIMESTAMP '2026-01-17 10:50:00.000000');

-- Pricing for SmartPhone Pro Max variants

INSERT INTO pricing (pricing_id, variant_id, selling_price, mrp, discount_percent, currency, active, created_at, updated_at)
VALUES (CAST('c6a2b3c4-d5e6-4789-c012-345678901234' AS UUID), CAST('b6a2b3c4-d5e6-4789-b012-345678901234' AS UUID), 49999.0, 59999.0, 17, 'INR', true, TIMESTAMP '2026-01-17 10:50:00.000000', TIMESTAMP '2026-01-17 10:50:00.000000');

INSERT INTO pricing (pricing_id, variant_id, selling_price, mrp, discount_percent, currency, active, created_at, updated_at)
VALUES (CAST('c7a2b3c4-d5e6-4789-c012-345678901234' AS UUID), CAST('b7a2b3c4-d5e6-4789-b012-345678901234' AS UUID), 59999.0, 69999.0, 14, 'INR', true, TIMESTAMP '2026-01-17 10:50:00.000000', TIMESTAMP '2026-01-17 10:50:00.000000');

INSERT INTO pricing (pricing_id, variant_id, selling_price, mrp, discount_percent, currency, active, created_at, updated_at)
VALUES (CAST('c8a2b3c4-d5e6-4789-c012-345678901234' AS UUID), CAST('b8a2b3c4-d5e6-4789-b012-345678901234' AS UUID), 49999.0, 59999.0, 17, 'INR', true, TIMESTAMP '2026-01-17 10:50:00.000000', TIMESTAMP '2026-01-17 10:50:00.000000');

INSERT INTO pricing (pricing_id, variant_id, selling_price, mrp, discount_percent, currency, active, created_at, updated_at)
VALUES (CAST('c9a2b3c4-d5e6-4789-c012-345678901234' AS UUID), CAST('b9a2b3c4-d5e6-4789-b012-345678901234' AS UUID), 59999.0, 69999.0, 14, 'INR', true, TIMESTAMP '2026-01-17 10:50:00.000000', TIMESTAMP '2026-01-17 10:50:00.000000');

-- Inventory for SmartPhone Pro Max variants

INSERT INTO inventory (inventory_id, variant_id, in_stock, created_at, updated_at)
VALUES (CAST('d6a2b3c4-d5e6-4789-d012-345678901234' AS UUID), CAST('b6a2b3c4-d5e6-4789-b012-345678901234' AS UUID), true, TIMESTAMP '2026-01-17 10:50:00.000000', TIMESTAMP '2026-01-17 10:50:00.000000');

INSERT INTO inventory (inventory_id, variant_id, in_stock, created_at, updated_at)
VALUES (CAST('d7a2b3c4-d5e6-4789-d012-345678901234' AS UUID), CAST('b7a2b3c4-d5e6-4789-b012-345678901234' AS UUID), true, TIMESTAMP '2026-01-17 10:50:00.000000', TIMESTAMP '2026-01-17 10:50:00.000000');

INSERT INTO inventory (inventory_id, variant_id, in_stock, created_at, updated_at)
VALUES (CAST('d8a2b3c4-d5e6-4789-d012-345678901234' AS UUID), CAST('b8a2b3c4-d5e6-4789-b012-345678901234' AS UUID), false, TIMESTAMP '2026-01-17 10:50:00.000000', TIMESTAMP '2026-01-17 10:50:00.000000');

INSERT INTO inventory (inventory_id, variant_id, in_stock, created_at, updated_at)
VALUES (CAST('d9a2b3c4-d5e6-4789-d012-345678901234' AS UUID), CAST('b9a2b3c4-d5e6-4789-b012-345678901234' AS UUID), true, TIMESTAMP '2026-01-17 10:50:00.000000', TIMESTAMP '2026-01-17 10:50:00.000000');

-- Product 4: Face Moisturizer (Beauty & Personal Care - Skincare)
-- Product for Face Care (product_group_id: d3a4b5c6-d7e8-4901-a234-567890123456)

INSERT INTO product (product_id, product_group_id, name, brand, code, primary_image_url, display_order, enabled, created_at, updated_at)
VALUES (CAST('f4a2b3c4-d5e6-4789-f012-345678901234' AS UUID), CAST('d3a4b5c6-d7e8-4901-a234-567890123456' AS UUID), 'Hydrating Face Moisturizer', 'GlowSkin', 'HYDRATING_FACE_MOISTURIZER', 'https://example.com/moisturizer.jpg', 1, true, TIMESTAMP '2026-01-17 10:50:00.000000', TIMESTAMP '2026-01-17 10:50:00.000000');

-- Variants for Hydrating Face Moisturizer (product_id: f4a2b3c4-d5e6-4789-f012-345678901234)

INSERT INTO variant (variant_id, product_id, label, enabled, is_default, display_order, created_at, updated_at)
VALUES (CAST('baa2b3c4-d5e6-4789-b012-345678901234' AS UUID), CAST('f4a2b3c4-d5e6-4789-f012-345678901234' AS UUID), '50 ml', true, true, 1, TIMESTAMP '2026-01-17 10:50:00.000000', TIMESTAMP '2026-01-17 10:50:00.000000');

INSERT INTO variant (variant_id, product_id, label, enabled, is_default, display_order, created_at, updated_at)
VALUES (CAST('bba2b3c4-d5e6-4789-b012-345678901234' AS UUID), CAST('f4a2b3c4-d5e6-4789-f012-345678901234' AS UUID), '100 ml', true, false, 2, TIMESTAMP '2026-01-17 10:50:00.000000', TIMESTAMP '2026-01-17 10:50:00.000000');

INSERT INTO variant (variant_id, product_id, label, enabled, is_default, display_order, created_at, updated_at)
VALUES (CAST('bca2b3c4-d5e6-4789-b012-345678901234' AS UUID), CAST('f4a2b3c4-d5e6-4789-f012-345678901234' AS UUID), '200 ml', true, false, 3, TIMESTAMP '2026-01-17 10:50:00.000000', TIMESTAMP '2026-01-17 10:50:00.000000');

-- Pricing for Hydrating Face Moisturizer variants

INSERT INTO pricing (pricing_id, variant_id, selling_price, mrp, discount_percent, currency, active, created_at, updated_at)
VALUES (CAST('caa2b3c4-d5e6-4789-c012-345678901234' AS UUID), CAST('baa2b3c4-d5e6-4789-b012-345678901234' AS UUID), 299.0, 399.0, 25, 'INR', true, TIMESTAMP '2026-01-17 10:50:00.000000', TIMESTAMP '2026-01-17 10:50:00.000000');

INSERT INTO pricing (pricing_id, variant_id, selling_price, mrp, discount_percent, currency, active, created_at, updated_at)
VALUES (CAST('cba2b3c4-d5e6-4789-c012-345678901234' AS UUID), CAST('bba2b3c4-d5e6-4789-b012-345678901234' AS UUID), 549.0, 699.0, 21, 'INR', true, TIMESTAMP '2026-01-17 10:50:00.000000', TIMESTAMP '2026-01-17 10:50:00.000000');

INSERT INTO pricing (pricing_id, variant_id, selling_price, mrp, discount_percent, currency, active, created_at, updated_at)
VALUES (CAST('cca2b3c4-d5e6-4789-c012-345678901234' AS UUID), CAST('bca2b3c4-d5e6-4789-b012-345678901234' AS UUID), 999.0, 1299.0, 23, 'INR', true, TIMESTAMP '2026-01-17 10:50:00.000000', TIMESTAMP '2026-01-17 10:50:00.000000');

-- Inventory for Hydrating Face Moisturizer variants

INSERT INTO inventory (inventory_id, variant_id, in_stock, created_at, updated_at)
VALUES (CAST('daa2b3c4-d5e6-4789-d012-345678901234' AS UUID), CAST('baa2b3c4-d5e6-4789-b012-345678901234' AS UUID), true, TIMESTAMP '2026-01-17 10:50:00.000000', TIMESTAMP '2026-01-17 10:50:00.000000');

INSERT INTO inventory (inventory_id, variant_id, in_stock, created_at, updated_at)
VALUES (CAST('dba2b3c4-d5e6-4789-d012-345678901234' AS UUID), CAST('bba2b3c4-d5e6-4789-b012-345678901234' AS UUID), true, TIMESTAMP '2026-01-17 10:50:00.000000', TIMESTAMP '2026-01-17 10:50:00.000000');

INSERT INTO inventory (inventory_id, variant_id, in_stock, created_at, updated_at)
VALUES (CAST('dca2b3c4-d5e6-4789-d012-345678901234' AS UUID), CAST('bca2b3c4-d5e6-4789-b012-345678901234' AS UUID), true, TIMESTAMP '2026-01-17 10:50:00.000000', TIMESTAMP '2026-01-17 10:50:00.000000');

-- Product 5: Men's Casual T-Shirt (Fashion - Men's Clothing)
-- Product for Casual Wear (product_group_id: a0d1e2f3-a4b5-4678-a901-234567890123)

INSERT INTO product (product_id, product_group_id, name, brand, code, primary_image_url, display_order, enabled, created_at, updated_at)
VALUES (CAST('f5a2b3c4-d5e6-4789-f012-345678901234' AS UUID), CAST('a0d1e2f3-a4b5-4678-a901-234567890123' AS UUID), 'Classic Cotton T-Shirt', 'StyleWear', 'CLASSIC_COTTON_TSHIRT', 'https://example.com/tshirt.jpg', 1, true, TIMESTAMP '2026-01-17 10:50:00.000000', TIMESTAMP '2026-01-17 10:50:00.000000');

-- Variants for Classic Cotton T-Shirt (product_id: f5a2b3c4-d5e6-4789-f012-345678901234)

INSERT INTO variant (variant_id, product_id, label, enabled, is_default, display_order, created_at, updated_at)
VALUES (CAST('bda2b3c4-d5e6-4789-b012-345678901234' AS UUID), CAST('f5a2b3c4-d5e6-4789-f012-345678901234' AS UUID), 'S - Black', true, true, 1, TIMESTAMP '2026-01-17 10:50:00.000000', TIMESTAMP '2026-01-17 10:50:00.000000');

INSERT INTO variant (variant_id, product_id, label, enabled, is_default, display_order, created_at, updated_at)
VALUES (CAST('bea2b3c4-d5e6-4789-b012-345678901234' AS UUID), CAST('f5a2b3c4-d5e6-4789-f012-345678901234' AS UUID), 'M - Black', true, false, 2, TIMESTAMP '2026-01-17 10:50:00.000000', TIMESTAMP '2026-01-17 10:50:00.000000');

INSERT INTO variant (variant_id, product_id, label, enabled, is_default, display_order, created_at, updated_at)
VALUES (CAST('bfa2b3c4-d5e6-4789-b012-345678901234' AS UUID), CAST('f5a2b3c4-d5e6-4789-f012-345678901234' AS UUID), 'L - Black', true, false, 3, TIMESTAMP '2026-01-17 10:50:00.000000', TIMESTAMP '2026-01-17 10:50:00.000000');

INSERT INTO variant (variant_id, product_id, label, enabled, is_default, display_order, created_at, updated_at)
VALUES (CAST('b0a2b3c4-d5e6-4789-b012-345678901234' AS UUID), CAST('f5a2b3c4-d5e6-4789-f012-345678901234' AS UUID), 'XL - Black', true, false, 4, TIMESTAMP '2026-01-17 10:50:00.000000', TIMESTAMP '2026-01-17 10:50:00.000000');

INSERT INTO variant (variant_id, product_id, label, enabled, is_default, display_order, created_at, updated_at)
VALUES (CAST('b1a2b3c4-d5e6-4789-b012-345678901235' AS UUID), CAST('f5a2b3c4-d5e6-4789-f012-345678901234' AS UUID), 'M - White', true, false, 5, TIMESTAMP '2026-01-17 10:50:00.000000', TIMESTAMP '2026-01-17 10:50:00.000000');

-- Pricing for Classic Cotton T-Shirt variants

INSERT INTO pricing (pricing_id, variant_id, selling_price, mrp, discount_percent, currency, active, created_at, updated_at)
VALUES (CAST('cda2b3c4-d5e6-4789-c012-345678901234' AS UUID), CAST('bda2b3c4-d5e6-4789-b012-345678901234' AS UUID), 399.0, 499.0, 20, 'INR', true, TIMESTAMP '2026-01-17 10:50:00.000000', TIMESTAMP '2026-01-17 10:50:00.000000');

INSERT INTO pricing (pricing_id, variant_id, selling_price, mrp, discount_percent, currency, active, created_at, updated_at)
VALUES (CAST('cea2b3c4-d5e6-4789-c012-345678901234' AS UUID), CAST('bea2b3c4-d5e6-4789-b012-345678901234' AS UUID), 399.0, 499.0, 20, 'INR', true, TIMESTAMP '2026-01-17 10:50:00.000000', TIMESTAMP '2026-01-17 10:50:00.000000');

INSERT INTO pricing (pricing_id, variant_id, selling_price, mrp, discount_percent, currency, active, created_at, updated_at)
VALUES (CAST('cfa2b3c4-d5e6-4789-c012-345678901234' AS UUID), CAST('bfa2b3c4-d5e6-4789-b012-345678901234' AS UUID), 399.0, 499.0, 20, 'INR', true, TIMESTAMP '2026-01-17 10:50:00.000000', TIMESTAMP '2026-01-17 10:50:00.000000');

INSERT INTO pricing (pricing_id, variant_id, selling_price, mrp, discount_percent, currency, active, created_at, updated_at)
VALUES (CAST('c0a2b3c4-d5e6-4789-c012-345678901234' AS UUID), CAST('b0a2b3c4-d5e6-4789-b012-345678901234' AS UUID), 399.0, 499.0, 20, 'INR', true, TIMESTAMP '2026-01-17 10:50:00.000000', TIMESTAMP '2026-01-17 10:50:00.000000');

INSERT INTO pricing (pricing_id, variant_id, selling_price, mrp, discount_percent, currency, active, created_at, updated_at)
VALUES (CAST('c1a2b3c4-d5e6-4789-c012-345678901235' AS UUID), CAST('b1a2b3c4-d5e6-4789-b012-345678901235' AS UUID), 399.0, 499.0, 20, 'INR', true, TIMESTAMP '2026-01-17 10:50:00.000000', TIMESTAMP '2026-01-17 10:50:00.000000');

-- Inventory for Classic Cotton T-Shirt variants

INSERT INTO inventory (inventory_id, variant_id, in_stock, created_at, updated_at)
VALUES (CAST('dda2b3c4-d5e6-4789-d012-345678901234' AS UUID), CAST('bda2b3c4-d5e6-4789-b012-345678901234' AS UUID), true, TIMESTAMP '2026-01-17 10:50:00.000000', TIMESTAMP '2026-01-17 10:50:00.000000');

INSERT INTO inventory (inventory_id, variant_id, in_stock, created_at, updated_at)
VALUES (CAST('dea2b3c4-d5e6-4789-d012-345678901234' AS UUID), CAST('bea2b3c4-d5e6-4789-b012-345678901234' AS UUID), true, TIMESTAMP '2026-01-17 10:50:00.000000', TIMESTAMP '2026-01-17 10:50:00.000000');

INSERT INTO inventory (inventory_id, variant_id, in_stock, created_at, updated_at)
VALUES (CAST('dfa2b3c4-d5e6-4789-d012-345678901234' AS UUID), CAST('bfa2b3c4-d5e6-4789-b012-345678901234' AS UUID), false, TIMESTAMP '2026-01-17 10:50:00.000000', TIMESTAMP '2026-01-17 10:50:00.000000');

INSERT INTO inventory (inventory_id, variant_id, in_stock, created_at, updated_at)
VALUES (CAST('d0a2b3c4-d5e6-4789-d012-345678901234' AS UUID), CAST('b0a2b3c4-d5e6-4789-b012-345678901234' AS UUID), true, TIMESTAMP '2026-01-17 10:50:00.000000', TIMESTAMP '2026-01-17 10:50:00.000000');

INSERT INTO inventory (inventory_id, variant_id, in_stock, created_at, updated_at)
VALUES (CAST('d1a2b3c4-d5e6-4789-d012-345678901235' AS UUID), CAST('b1a2b3c4-d5e6-4789-b012-345678901235' AS UUID), true, TIMESTAMP '2026-01-17 10:50:00.000000', TIMESTAMP '2026-01-17 10:50:00.000000');