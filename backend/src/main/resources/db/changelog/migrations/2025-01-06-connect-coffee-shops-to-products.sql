--liquibase formatted sql

--changeset ilya-kislitsyn:13
CREATE TABLE coffee_shops_products(
    coffee_shop_id BIGINT REFERENCES coffee_shops(id) ON DELETE CASCADE,
    product_id     BIGINT REFERENCES products(id) ON DELETE CASCADE,
    PRIMARY KEY (coffee_shop_id, product_id)
);

COMMENT ON TABLE coffee_shops_products IS 'Many-to-Many связь между кофейнями и продуктами';
COMMENT ON COLUMN coffee_shops_products.product_id IS 'id продукта(внешний ключ)';
COMMENT ON COLUMN coffee_shops_products.coffee_shop_id IS 'id кофейни(внешний ключ';