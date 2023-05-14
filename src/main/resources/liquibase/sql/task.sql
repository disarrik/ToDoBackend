-- liquibase formatted sql
-- changeset disarra:1
CREATE TABLE task (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(127) NOT NULL,
    description VARCHAR(127) NOT NULL,
    group_id BIGINT NOT NULL REFERENCES "group"(id)
);

-- changeset disarra:add_deadline
ALTER TABLE task ADD COLUMN deadline TIMESTAMP NOT NUlL DEFAULT now();

--changeset disarra:add_deadline_index
CREATE INDEX task_deadline_index ON task (deadline);
