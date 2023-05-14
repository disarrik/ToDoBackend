package ru.disarra.todo.group

interface GroupRepository {

    fun createGroup(group: Group): Long
    fun addToGroup(groupId: Long, userId: Long)
    fun getGroups(userId: Long): List<Group>

}