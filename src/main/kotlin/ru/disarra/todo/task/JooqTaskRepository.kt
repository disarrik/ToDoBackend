package ru.disarra.todo.task

import org.jooq.DSLContext
import org.jooq.impl.DSL.value
import org.springframework.stereotype.Repository
import ru.disarra.todo.jooq.Tables

@Repository
class JooqTaskRepository(
    val dslContext: DSLContext
): TaskRepository {

    override fun get(userId: Long): List<Task> {
        val userGroupsIds = dslContext.select(Tables.USER_GROUP.GROUP_ID)
            .from(Tables.USER_GROUP)
            .where(Tables.USER_GROUP.USER_ID.eq(userId))

        return dslContext.select(*Tables.TASK.fields(), Tables.TASK_STATUS.DONE)
            .from(Tables.TASK
                .join(Tables.TASK_STATUS)
                .on(Tables.TASK_STATUS.USER_ID.eq(userId))
                .and(Tables.TASK_STATUS.TASK_ID.eq(Tables.TASK.ID)))
            .where(Tables.TASK.GROUP_ID.`in`(userGroupsIds))
            .fetch { r ->
                Task(
                    id = r.get(Tables.TASK.ID),
                    groupId = r.get(Tables.TASK.GROUP_ID),
                    name = r.get(Tables.TASK.NAME),
                    description = r.get(Tables.TASK.DESCRIPTION),
                    done = r.get(Tables.TASK_STATUS.DONE)
                )
            }
    }

    override fun add(task: Task): Long {
        return dslContext.insertInto(Tables.TASK)
            .set(Tables.TASK.GROUP_ID, task.groupId)
            .set(Tables.TASK.NAME, task.name)
            .set(Tables.TASK.DESCRIPTION, task.description)
            .returning()
            .fetchOne { r -> r.get(Tables.TASK.ID) }!!
    }

    override fun addTaskStatusesForAllGroupMembers(groupId: Long, taskId: Long) {
        dslContext.insertInto(Tables.TASK_STATUS,
            Tables.TASK_STATUS.TASK_ID, Tables.TASK_STATUS.USER_ID, Tables.TASK_STATUS.DONE)
            .select(
                dslContext.select(value(taskId), Tables.USER_GROUP.USER_ID, value(false))
                    .from(Tables.USER_GROUP)
                    .where(Tables.USER_GROUP.GROUP_ID.eq(groupId))
            )
            .execute()
    }

    override fun markDone(userId: Long, taskId: Long, done: Boolean) {
        dslContext.update(Tables.TASK_STATUS)
            .set(Tables.TASK_STATUS.DONE, done)
            .where(Tables.TASK_STATUS.USER_ID.eq(userId))
            .and(Tables.TASK_STATUS.TASK_ID.eq(taskId))
            .execute()
    }

    override fun addTaskStatusForNewUser(userId: Long, groupId: Long) {
        dslContext
            .insertInto(
                Tables.TASK_STATUS,
                Tables.TASK_STATUS.TASK_ID, Tables.TASK_STATUS.USER_ID, Tables.TASK_STATUS.DONE
            )
            .select(
                dslContext
                    .select(
                        Tables.TASK.ID,
                        value(userId),
                        value(false))
                    .from(Tables.TASK)
                    .where(Tables.TASK.GROUP_ID.eq(groupId))
            )
            .execute()
    }


}
