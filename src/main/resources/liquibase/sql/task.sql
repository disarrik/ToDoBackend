-- liquibase formatted sql
-- changeset disarra:1
CREATE TABLE task (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(127) NOT NULL,
    description VARCHAR(127) NOT NULL,
    group_id BIGINT NOT NULL REFERENCES "group"(id)
);
