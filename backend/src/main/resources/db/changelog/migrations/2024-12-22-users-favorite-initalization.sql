--liquibase formatted sql

--changeset igor-chaiko:5
CREATE TABLE users_favorite
(
    id            BIGSERIAL                   PRIMARY KEY,
    user_id       BIGINT REFERENCES users(id) NOT NULL,
    entity_id     BIGINT                      NOT NULL,
    entity_type   TEXT                        NOT NULL
);

COMMENT ON TABLE users_favorite IS 'Сущности, которые пользователь добавил в избраннле';
COMMENT ON COLUMN users_favorite.id IS 'Идентификатор записи';
COMMENT ON COLUMN users_favorite.entity_id IS 'Идентификатор сущности';
COMMENT ON COLUMN users_favorite.entity_type IS 'Тип сущности';
