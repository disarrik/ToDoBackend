package ru.disarra.todo.security

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class RegisterController(val userRepository: UserRepository) {

    @PostMapping("/register")
    fun register(@RequestBody newUserDto: NewUserDto) {
        userRepository.createUser(newUserDto.username, newUserDto.password)
    }

}
