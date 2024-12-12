--liquibase formatted sql

--changeset ilya-kislitsyn:4
CREATE TABLE coffee_shops
(
    id          BIGSERIAL  PRIMARY KEY,
    name        TEXT       NOT NULL,
    description TEXT,
    address     TEXT       NOT NULL,
    score       FLOAT,
    laptop      BOOLEAN,
    dogs        BOOLEAN,
    seats       BOOLEAN
);

COMMENT ON TABLE coffee_shops IS 'refresh токены';
COMMENT ON COLUMN coffee_shops.id IS 'Идентификатор кофейни';
COMMENT ON COLUMN coffee_shops.name IS 'Название кофейни';
COMMENT ON COLUMN coffee_shops.description IS 'Описание кофейни';
COMMENT ON COLUMN coffee_shops.address IS 'Адрес кофейни';
COMMENT ON COLUMN coffee_shops.score IS 'Оценка кофейни';
COMMENT ON COLUMN coffee_shops.laptop IS 'Можно ли с ноутбуком';
COMMENT ON COLUMN coffee_shops.dogs IS 'Можно ли с животными';
COMMENT ON COLUMN coffee_shops.seats IS 'Можно ли посидеть';



CREATE TYPE drink_type AS ENUM (
    'black_coffee',
    'white_coffee',
    'tea_black',
    'tea_green',
    'tea_herbal',
    'cocoa',
    'lemonade',
    'matcha',
    'smoothie');

CREATE TYPE drink_temperature AS ENUM (
    'cold',
    'hot'
    );

CREATE TABLE drinks
(
    id   BIGSERIAL PRIMARY KEY,
    name TEXT NOT NULL,
    type drink_type,
    rating FLOAT,
    composition TEXT,
    temperature drink_temperature
);

COMMENT ON TABLE drinks IS 'Готовые напитки';
COMMENT ON COLUMN drinks.name IS 'Название напитка';
COMMENT ON COLUMN drinks.type IS 'Тип напитка';
COMMENT ON COLUMN drinks.rating IS 'Оценка напитка';
COMMENT ON COLUMN drinks.composition IS 'Состав напитка';
COMMENT ON COLUMN drinks.temperature IS 'Температура напитка (холодный/горячий)';


CREATE TABLE coffee_shop_reviews(
                                    id                 BIGSERIAL PRIMARY KEY,
                                    coffee_shop_id     BIGSERIAL REFERENCES coffee_shops(id) ON DELETE CASCADE,
                                    review             TEXT,
                                    score              FLOAT,
                                    user_id            BIGSERIAL REFERENCES users(id),
                                    created_at         TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

COMMENT ON COLUMN coffee_shop_reviews.coffee_shop_id IS 'Ссылка на идентификатор кофейни, к которой относится отзыв';
COMMENT ON COLUMN coffee_shop_reviews.review IS 'Текст отзыва о кафе';
COMMENT ON COLUMN coffee_shop_reviews.score IS 'Оценка кофейни';
COMMENT ON COLUMN coffee_shop_reviews.user_id IS 'id пользователя, оставившего отзыв';
COMMENT ON COLUMN coffee_shop_reviews.created_at IS 'Дата и время создания отзыва, по умолчанию текущее время';



CREATE TYPE product_type AS ENUM (
    'drip_bags',
    'instant_coffee',
    'coffee_beans',
    'black_tea',
    'green_tea',
    'herbal_tee'
    );


CREATE TABLE products
(
    id   BIGSERIAL PRIMARY KEY,
    name TEXT NOT NULL,
    type product_type,
    rating FLOAT,
    composition TEXT
);