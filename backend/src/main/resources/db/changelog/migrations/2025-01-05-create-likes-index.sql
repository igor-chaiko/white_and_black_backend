--liquibase formatted sql

--changeset kislitsyn-ilya:7
DROP INDEX IF EXISTS users_favorite_user_id_idx;
CREATE INDEX ON users_favorite (user_id);