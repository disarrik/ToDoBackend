package ru.disarra.todo.task

import java.time.LocalDateTime

data class Task(
    val id: Long? = null,
    val name: String,
    val description: String,
    val done: Boolean? = null,
    val groupId: Long,
    val deadline: LocalDateTime
    )
