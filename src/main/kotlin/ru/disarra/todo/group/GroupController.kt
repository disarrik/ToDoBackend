package ru.disarra.todo.group

import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/group")
@CrossOrigin
class GroupController(val groupService: GroupService) {

    @GetMapping
    @PreAuthorize("hasAuthority('LOGIN_'.concat(#userLogin))")
    fun getGroups(@RequestParam userLogin: String): List<Group> {
        return groupService.getGroups(userLogin)
    }

    @PostMapping
    @PreAuthorize("hasAuthority('LOGIN_'.concat(#group.adminLogin))")
    fun createGroup(@RequestBody group: Group) {
        groupService.createGroup(group)
    }

    @PutMapping
    @PreAuthorize("hasAuthority('ADMIN_'.concat(#groupId))")
    fun addUserToGroup(@RequestParam groupId: Long, @RequestParam userLogin: String) {
        groupService.addUserToGroup(groupId, userLogin)
    }

}