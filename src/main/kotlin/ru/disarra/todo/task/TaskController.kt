package ru.disarra.todo.task

import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin
@RequestMapping("/task")
class TaskController(val taskService: TaskService) {

    @PostMapping
    fun addTask(@RequestBody newTask: Task) {
        taskService.addTask(newTask)
    }

    @GetMapping
    fun getTasks(@RequestParam userLogin: String): List<Task> {
        return taskService.getTasks(userLogin)
    }

    @PatchMapping
    fun markDone(@RequestParam userLogin: String, @RequestParam taskId: Long, @RequestParam done: Boolean) {
        taskService.markDone(userLogin, taskId, done)
    }

}