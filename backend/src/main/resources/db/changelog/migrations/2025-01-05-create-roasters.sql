--liquibase formatted sql

--changeset ilya-kislitsyn:8
CREATE TABLE roasters
(
    id          BIGSERIAL  PRIMARY KEY,
    name        TEXT       NOT NULL,
    description TEXT,
    score_sum    INT,
    score_count  INT
);

COMMENT ON TABLE roasters IS 'refresh токены';
COMMENT ON COLUMN roasters.id IS 'Идентификатор обжарщика';
COMMENT ON COLUMN roasters.name IS 'Название обжарщика';
COMMENT ON COLUMN roasters.description IS 'Описание обжарщика';
COMMENT ON COLUMN roasters.score_count IS 'Оценка обжарщика (количество)';
COMMENT ON COLUMN roasters.score_sum IS 'Оценка обжарщика (сумма)';

--changeset ilya-kislitsyn:9
CREATE TABLE roaster_reviews(
    id                 BIGSERIAL PRIMARY KEY,
    roaster_id         BIGINT REFERENCES roasters(id) ON DELETE CASCADE,
    review             TEXT,
    score              INT,
    user_id            BIGINT REFERENCES users(id),
    created_at         TIMESTAMP WITH TIME ZONE DEFAULT NOW()
);

COMMENT ON TABLE roaster_reviews IS 'Отзывы на обжарщиков';
COMMENT ON COLUMN roaster_reviews.roaster_id IS 'Ссылка на идентификатор обжарщика, к которому относится отзыв';
COMMENT ON COLUMN roaster_reviews.review IS 'Текст отзыва об обжарщике';
COMMENT ON COLUMN roaster_reviews.score IS 'Оценка обжарщика';
COMMENT ON COLUMN roaster_reviews.user_id IS 'id пользователя, оставившего отзыв';
COMMENT ON COLUMN roaster_reviews.created_at IS 'Дата и время создания отзыва, по умолчанию текущее время';

--changeset ilya-kislitsyn:10
CREATE INDEX ON roaster_reviews(roaster_id);
CREATE INDEX ON roaster_reviews(user_id);

--changeset ilya-kislitsyn:11
CREATE TABLE products
(
    id             BIGSERIAL PRIMARY KEY,
    roaster_id     BIGINT NOT NULL REFERENCES roasters(id) ON DELETE CASCADE,
    roaster_name   TEXT,
    name           TEXT NOT NULL,
    type           TEXT,
    subtype        TEXT,
    score_sum      FLOAT,
    score_count    FLOAT,
    composition    TEXT
);

COMMENT ON TABLE products IS 'Готовые продукты';
COMMENT ON COLUMN products.name IS 'Название продукта';
COMMENT ON COLUMN products.type IS 'Тип продукта';
COMMENT ON COLUMN products.score_sum IS 'Оценка продукта (сумма)';
COMMENT ON COLUMN products.score_count IS 'Оценка продукта (количество)';
COMMENT ON COLUMN products.composition IS 'Состав продукта';
COMMENT ON COLUMN products.roaster_name IS 'Производитель';
COMMENT ON COLUMN products.subtype IS 'Подтип продукта';

--changeset ilya-kislitsyn:12
CREATE TABLE product_reviews(
      id                 BIGSERIAL PRIMARY KEY,
      product_id           BIGINT REFERENCES products(id) ON DELETE CASCADE,
      review             TEXT,
      score              INT,
      user_id            BIGINT REFERENCES users(id),
      created_at         TIMESTAMP WITH TIME ZONE DEFAULT NOW()
);

COMMENT ON TABLE product_reviews IS 'Отзывы на продукты';
COMMENT ON COLUMN product_reviews.product_id IS 'Ссылка на идентификатор продукта, к которому относится отзыв';
COMMENT ON COLUMN product_reviews.review IS 'Текст отзыва о продукте';
COMMENT ON COLUMN product_reviews.score IS 'Оценка продукта';
COMMENT ON COLUMN product_reviews.user_id IS 'id пользователя, оставившего отзыв';
COMMENT ON COLUMN product_reviews.created_at IS 'Дата и время создания отзыва, по умолчанию текущее время';