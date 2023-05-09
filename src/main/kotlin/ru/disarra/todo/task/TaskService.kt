package ru.disarra.todo.task


interface TaskService {

    fun getTasks(userLogin: String): List<Task>
    fun addTask(task: Task)
    fun markDone(userLogin: String, taskId: Long, done: Boolean)

}