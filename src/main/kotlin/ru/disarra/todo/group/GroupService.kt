package ru.disarra.todo.group

interface GroupService {

    fun createGroup(group: Group)
    fun getGroups(userLogin: String): List<Group>
    fun addUserToGroup(groupId: Long, userLogin: String)
}