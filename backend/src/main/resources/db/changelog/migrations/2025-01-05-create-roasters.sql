--liquibase formatted sql

--changeset ilya-kislitsyn:7
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

--changeset ilya-kislitsyn:8
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
