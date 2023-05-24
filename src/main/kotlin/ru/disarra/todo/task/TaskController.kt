package ru.disarra.todo.task

import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin
@RequestMapping("/task")
class TaskController(val taskService: TaskService) {

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN_'.concat(#newTask.groupId))")
    fun addTask(@RequestBody newTask: Task) {
        taskService.addTask(newTask)
    }

    @GetMapping
    @PreAuthorize("hasAuthority('LOGIN_'.concat(#userLogin))")
    fun getTasks(@RequestParam userLogin: String): List<Task> {
        return taskService.getTasks(userLogin)
    }

    @PatchMapping
    @PreAuthorize("hasAuthority('LOGIN_'.concat(#userLogin))")
    fun markDone(@RequestParam userLogin: String, @RequestParam taskId: Long, @RequestParam done: Boolean) {
        taskService.markDone(userLogin, taskId, done)
    }

}