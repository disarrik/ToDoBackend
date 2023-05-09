package ru.disarra.todo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity

@SpringBootApplication
@EnableMethodSecurity
class ToDoBackendApplication

fun main(args: Array<String>) {
    runApplication<ToDoBackendApplication>(*args)
}
