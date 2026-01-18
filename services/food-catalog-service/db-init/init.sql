CREATE TABLE IF NOT EXISTS food_item
(
    id               BIGINT AUTO_INCREMENT PRIMARY KEY,
    item_name        VARCHAR(255) NOT NULL,
    item_description TEXT,                            -- Using TEXT for longer descriptions
    is_vegetarian    TINYINT(1)            DEFAULT 0, -- Matches Java boolean
    price            DECIMAL(10, 2),                  -- Matches Java Number (supports decimals)
    restaurant_id    INT,
    quantity         INT          NOT NULL DEFAULT 0  -- Matches @Column definition
);

INSERT INTO food_item (id, is_vegetarian, item_description, item_name, price, quantity, restaurant_id)
VALUES (1, 1, 'Delicious vegetarian dish', 'Vegetable Biryani', 300, 0, 1),
       (2, 0, 'Succulent chicken in a creamy sauce', 'Butter Chicken', 310, 0, 1),
       (3, 1, 'Mouthwatering lentil curry', 'Dal Tadka', 350, 0, 2),
       (4, 0, 'Spicy and flavorful lamb curry', 'Rogan Josh', 315, 0, 2),
       (5, 1, 'Crispy and spicy potato patties', 'Aloo Tikki', 500, 0, 3),
       (6, 1, 'Paneer cubes marinated in tandoori spices', 'Tandoori Paneer Tikka', 900, 0, 3),
       (7, 0, 'Fragrant and aromatic rice dish', 'Chicken Biryani', 120, 0, 4),
       (8, 1, 'Mixed vegetable curry', 'Vegetable Korma', 110, 0, 4),
       (9, 0, 'Spicy and tangy shrimp curry', 'Goan Prawn Curry', 140, 0, 5),
       (10, 1, 'Fluffy Indian bread', 'Naan', 300, 0, 5),
       (11, 0, 'Chicken marinated in yogurt and spices', 'Chicken Tikka', 100, 0, 6),
       (12, 1, 'Aromatic rice pudding', 'Kheer', 600, 0, 6),
       (13, 1, 'Savory lentil donuts', 'Medu Vada', 400, 0, 7),
       (14, 0, 'Crispy crepe filled with spiced potatoes', 'Masala Dosa', 800, 0, 7),
       (15, 1, 'Refreshing yogurt-based drink', 'Mango Lassi', 500, 0, 8),
       (16, 0, 'Assorted Indian bread basket', 'Basket of Rotis', 700, 0, 8),
       (17, 1, 'Lentils cooked with mixed spices', 'Dal Fry', 350, 0, 1);
