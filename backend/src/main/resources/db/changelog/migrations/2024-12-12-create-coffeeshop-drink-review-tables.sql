--liquibase formatted sql

--changeset ilya-kislitsyn:4
CREATE TABLE coffee_shops
(
    id          BIGSERIAL  PRIMARY KEY,
    name        TEXT       NOT NULL,
    description TEXT,
    address     TEXT       NOT NULL,
    score_sum    INT,
    score_count  INT,
    withLaptop  BOOLEAN,
    withDog     BOOLEAN,
    seats       BOOLEAN
);

COMMENT ON TABLE coffee_shops IS 'refresh токены';
COMMENT ON COLUMN coffee_shops.id IS 'Идентификатор кофейни';
COMMENT ON COLUMN coffee_shops.name IS 'Название кофейни';
COMMENT ON COLUMN coffee_shops.description IS 'Описание кофейни';
COMMENT ON COLUMN coffee_shops.address IS 'Адрес кофейни';
COMMENT ON COLUMN coffee_shops.score_count IS 'Оценка кофейни (количество)';
COMMENT ON COLUMN coffee_shops.score_sum IS 'Оценка кофейни (сумма)';
COMMENT ON COLUMN coffee_shops.withLaptop IS 'Можно ли с ноутбуком';
COMMENT ON COLUMN coffee_shops.withDog IS 'Можно ли с животными';
COMMENT ON COLUMN coffee_shops.seats IS 'Можно ли посидеть';




CREATE TABLE drinks
(
    id             BIGSERIAL PRIMARY KEY,
    coffee_shop_id BIGINT NOT NULL REFERENCES coffee_shops(id) ON DELETE CASCADE,
    name           TEXT NOT NULL,
    type           TEXT,
    score_sum      FLOAT,
    score_count    FLOAT,
    composition    TEXT,
    coffee_shop    TEXT,
    temperature    TEXT
);

COMMENT ON TABLE drinks IS 'Готовые напитки';
COMMENT ON COLUMN drinks.name IS 'Название напитка';
COMMENT ON COLUMN drinks.type IS 'Тип напитка';
COMMENT ON COLUMN drinks.score_sum IS 'Оценка напитка (сумма)';
COMMENT ON COLUMN drinks.score_count IS 'Оценка напитка (количество)';
COMMENT ON COLUMN drinks.composition IS 'Состав напитка';
COMMENT ON COLUMN drinks.coffee_shop IS 'Кофейня, где он есть';
COMMENT ON COLUMN drinks.temperature IS 'Температура напитка (холодный/горячий)';

CREATE TABLE coffee_shop_reviews(
    id                 BIGSERIAL PRIMARY KEY,
    coffee_shop_id     BIGINT REFERENCES coffee_shops(id) ON DELETE CASCADE,
    review             TEXT,
    score              INT,
    user_id            BIGINT REFERENCES users(id),
    created_at         TIMESTAMP WITH TIME ZONE DEFAULT NOW()
);

COMMENT ON TABLE coffee_shop_reviews IS 'Отзывы на кофейни';
COMMENT ON COLUMN coffee_shop_reviews.coffee_shop_id IS 'Ссылка на идентификатор кофейни, к которой относится отзыв';
COMMENT ON COLUMN coffee_shop_reviews.review IS 'Текст отзыва о кафе';
COMMENT ON COLUMN coffee_shop_reviews.score IS 'Оценка кофейни';
COMMENT ON COLUMN coffee_shop_reviews.user_id IS 'id пользователя, оставившего отзыв';
COMMENT ON COLUMN coffee_shop_reviews.created_at IS 'Дата и время создания отзыва, по умолчанию текущее время';

CREATE TABLE drink_reviews(
    id                 BIGSERIAL PRIMARY KEY,
    drink_id           BIGINT REFERENCES drinks(id) ON DELETE CASCADE,
    review             TEXT,
    score              INT,
    user_id            BIGINT REFERENCES users(id),
    created_at         TIMESTAMP WITH TIME ZONE DEFAULT NOW()
);

COMMENT ON TABLE drink_reviews IS 'Отзывы на напитки';
COMMENT ON COLUMN drink_reviews.drink_id IS 'Ссылка на идентификатор напитка, к которому относится отзыв';
COMMENT ON COLUMN drink_reviews.review IS 'Текст отзыва о напитке';
COMMENT ON COLUMN drink_reviews.score IS 'Оценка напитка';
COMMENT ON COLUMN drink_reviews.user_id IS 'id пользователя, оставившего отзыв';
COMMENT ON COLUMN drink_reviews.created_at IS 'Дата и время создания отзыва, по умолчанию текущее время';