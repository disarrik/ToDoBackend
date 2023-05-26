-- liquibase formatted sql
-- changeset disarra:2
CREATE TABLE user_group (
    user_id BIGINT NOT NULL,
    group_id BIGINT NOT NULL,
    PRIMARY KEY (user_id, group_id)
);

CREATE INDEX user_group_user_id_index ON user_group(user_id);
CREATE INDEX user_group_group_id_index ON user_group(group_id);