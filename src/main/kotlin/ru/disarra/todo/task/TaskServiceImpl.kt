package ru.disarra.todo.task

import org.springframework.stereotype.Service
import org.springframework.transaction.support.TransactionTemplate
import ru.disarra.todo.security.UserRepository

@Service
class TaskServiceImpl(
    val taskRepository: TaskRepository,
    val transactionTemplate: TransactionTemplate,
    val userRepository: UserRepository): TaskService {

    override fun getTasks(userLogin: String): List<Task> {
        return transactionTemplate.execute {
            val userId = userRepository.getUserId(userLogin)
            return@execute taskRepository.get(userId)
        }!!
    }

    override fun addTask(task: Task) {
        transactionTemplate.execute { ts ->
            val taskId = taskRepository.add(task)
            taskRepository.addTaskStatusesForAllGroupMembers(task.groupId, taskId)
        }
    }

    override fun markDone(userLogin: String, taskId: Long, done: Boolean) {
        transactionTemplate.execute {
            val userId = userRepository.getUserId(userLogin)
            taskRepository.markDone(userId, taskId, done)
        }
    }
}