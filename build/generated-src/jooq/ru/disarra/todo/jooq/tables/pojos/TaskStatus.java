/*
 * This file is generated by jOOQ.
 */
package ru.disarra.todo.jooq.tables.pojos;


import java.io.Serializable;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class TaskStatus implements Serializable {

    private static final long serialVersionUID = 1L;

    private final Long taskId;
    private final Long userId;
    private final Boolean done;

    public TaskStatus(TaskStatus value) {
        this.taskId = value.taskId;
        this.userId = value.userId;
        this.done = value.done;
    }

    public TaskStatus(
        Long taskId,
        Long userId,
        Boolean done
    ) {
        this.taskId = taskId;
        this.userId = userId;
        this.done = done;
    }

    /**
     * Getter for <code>public.task_status.task_id</code>.
     */
    public Long getTaskId() {
        return this.taskId;
    }

    /**
     * Getter for <code>public.task_status.user_id</code>.
     */
    public Long getUserId() {
        return this.userId;
    }

    /**
     * Getter for <code>public.task_status.done</code>.
     */
    public Boolean getDone() {
        return this.done;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final TaskStatus other = (TaskStatus) obj;
        if (this.taskId == null) {
            if (other.taskId != null)
                return false;
        }
        else if (!this.taskId.equals(other.taskId))
            return false;
        if (this.userId == null) {
            if (other.userId != null)
                return false;
        }
        else if (!this.userId.equals(other.userId))
            return false;
        if (this.done == null) {
            if (other.done != null)
                return false;
        }
        else if (!this.done.equals(other.done))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.taskId == null) ? 0 : this.taskId.hashCode());
        result = prime * result + ((this.userId == null) ? 0 : this.userId.hashCode());
        result = prime * result + ((this.done == null) ? 0 : this.done.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("TaskStatus (");

        sb.append(taskId);
        sb.append(", ").append(userId);
        sb.append(", ").append(done);

        sb.append(")");
        return sb.toString();
    }
}
