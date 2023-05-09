package ru.disarra.todo.group

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController("/group")
class GroupController(val groupService: GroupService) {

    @GetMapping
    fun getGroups(@RequestParam userLogin: String) {
        groupService.getGroups(userLogin)
    }

    @PostMapping
    fun createGroup(@RequestBody group: Group) {
        groupService.createGroup(group)
    }

    @PutMapping
    fun addUserToGroup(@RequestParam groupId: Long, @RequestParam userLogin: String) {
        groupService.addUserToGroup(groupId, userLogin)
    }

}