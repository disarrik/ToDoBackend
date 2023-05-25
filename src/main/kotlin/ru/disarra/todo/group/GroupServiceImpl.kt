package ru.disarra.todo.group

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import org.springframework.transaction.support.TransactionTemplate
import ru.disarra.todo.security.GROUP_ADMIN_PREFIX
import ru.disarra.todo.security.UserRepository
import ru.disarra.todo.task.TaskRepository

@Service
class GroupServiceImpl(
    val groupRepository: GroupRepository,
    val taskRepository: TaskRepository,
    val transactionTemplate: TransactionTemplate,
    val userRepository: UserRepository): GroupService {

    override fun createGroup(group: Group) {
        transactionTemplate.execute { tx ->
            val groupId = groupRepository.createGroup(group)
            val userId = userRepository.getUserId(group.adminLogin!!)
            groupRepository.addToGroup(groupId, userId)
            taskRepository.addTaskStatusForNewUser(userId, groupId)
            val newAuthorities = SecurityContextHolder.getContext().authentication.authorities +
                    SimpleGrantedAuthority(GROUP_ADMIN_PREFIX + groupId.toString())
            val oldAuth = SecurityContextHolder.getContext().authentication
            val newAuth = UsernamePasswordAuthenticationToken(
                oldAuth.principal,
                oldAuth.credentials,
                newAuthorities
            )
            SecurityContextHolder.getContext().authentication = newAuth
        }
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