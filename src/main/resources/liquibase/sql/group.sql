-- liquibase formatted sql
-- changeset disarra:1
CREATE TABLE "group" (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(127) NOT NULL,
    admin_id BIGINT REFERENCES "user"(id)
);

CREATE INDEX groups_admin_id_index ON "group" (admin_id);