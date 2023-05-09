-- liquibase formatted sql
-- changeset disarra:1
CREATE TABLE "user" (
  id BIGSERIAL PRIMARY KEY NOT NULL,
  login VARCHAR(172) NOT NULL,
  passwordHash VARCHAR(511) NOT NULL,
  active boolean NOT NULL DEFAULT true
);

CREATE INDEX users_login_index ON "user" (login);

ALTER TABLE "user" ADD CONSTRAINT user_unique_login_constraint UNIQUE (login);