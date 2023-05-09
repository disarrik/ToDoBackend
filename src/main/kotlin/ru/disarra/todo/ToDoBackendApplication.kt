package ru.disarra.todo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ToDoBackendApplication

fun main(args: Array<String>) {
    runApplication<ToDoBackendApplication>(*args)
}
