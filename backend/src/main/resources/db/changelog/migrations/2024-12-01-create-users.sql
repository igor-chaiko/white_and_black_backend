--liquibase formatted sql

--changeset igor-chaiko:1
CREATE TABLE users
(
    id       BIGSERIAL PRIMARY KEY,
    login    TEXT       NOT NULL,
    password TEXT       NOT NULL,
    email    TEXT       NOT NULL
);

COMMENT ON TABLE users IS 'Пользователи';
COMMENT ON COLUMN users.id IS 'Идентификатор пользователя';
COMMENT ON COLUMN users.login IS 'Логин пользователя';
COMMENT ON COLUMN users.password IS 'Пароль пользователя';
COMMENT ON COLUMN users.email IS 'Адресс почты пользователя';
