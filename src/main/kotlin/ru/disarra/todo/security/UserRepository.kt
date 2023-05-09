package ru.disarra.todo.security

import org.springframework.security.core.userdetails.UserDetailsService

interface UserRepository : UserDetailsService {

   fun createUser(username: String, password: String)
   fun getUserId(userLogin: String): Long

}