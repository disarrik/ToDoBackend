-- liquibase formatted sql
-- changeset disarra:1
CREATE TABLE task_status (
    task_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    done boolean NOT NULL DEFAULT false,
    PRIMARY KEY (task_id, user_id)
);

CREATE INDEX task_status_task_id_index ON task_status(task_id);
CREATE INDEX task_status_user_id_index ON task_status(user_id);