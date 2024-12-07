--liquibase formatted sql

--changeset ilya-kislitsyn:3
CREATE TABLE refresh_tokens
(
    id       BIGSERIAL  PRIMARY KEY,
    token    TEXT       NOT NULL,
    username TEXT       NOT NULL
);

COMMENT ON TABLE refresh_tokens IS 'refresh токены';
COMMENT ON COLUMN refresh_tokens.id IS 'Идентификатор токена';
COMMENT ON COLUMN refresh_tokens.token IS 'Токен';
COMMENT ON COLUMN refresh_tokens.username IS 'Логин пользователя';

