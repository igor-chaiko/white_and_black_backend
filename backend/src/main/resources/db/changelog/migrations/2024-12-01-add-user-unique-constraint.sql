--liquibase formatted sql

--changeset igor-chaiko:2
ALTER TABLE users ADD CONSTRAINT unique_login UNIQUE (login);
