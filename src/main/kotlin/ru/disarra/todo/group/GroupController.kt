package ru.disarra.todo.group

import org.springframework.web.bind.annotation.*

@RestController("/group")
@CrossOrigin
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