package ru.disarra.todo.task

interface TaskRepository {

    fun get(userId: Long): List<Task>
    fun add(task: Task): Long
    fun addTaskStatusesForAllGroupMembers(groupId: Long, taskId: Long)
    fun markDone(userId: Long, taskId: Long, done: Boolean)
    fun addTaskStatusForNewUser(userId: Long, groupId: Long)

}