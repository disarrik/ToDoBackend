package ru.disarra.todo.group

import org.springframework.stereotype.Service
import org.springframework.transaction.support.TransactionTemplate
import ru.disarra.todo.security.UserRepository
import ru.disarra.todo.task.TaskRepository

@Service
class GroupServiceImpl(
    val groupRepository: GroupRepository,
    val taskRepository: TaskRepository,
    val transactionTemplate: TransactionTemplate,
    val userRepository: UserRepository): GroupService {

    override fun createGroup(group: Group) {
        groupRepository.createGroup(group)
    }

    override fun getGroups(userLogin: String): List<Group> {
        return transactionTemplate.execute {
            val userId = userRepository.getUserId(userLogin)
            return@execute groupRepository.getGroups(userId)
        }!!
    }

    override fun addUserToGroup(groupId: Long, userLogin: String) {
        transactionTemplate.execute { tx ->
            val userId = userRepository.getUserId(userLogin)
            groupRepository.addToGroup(groupId, userId)
            taskRepository.addTaskStatusForNewUser(userId, groupId)
        }
    }

}