package ru.disarra.todo.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain


@Configuration
class SecurityConfiguration {

    @Bean
    @Throws(Exception::class)
    fun filterChain(http: HttpSecurity): SecurityFilterChain? {
        http
            .csrf().disable()
            .authorizeHttpRequests()
            .anyRequest()
            .anonymous()
            .and()
            .httpBasic()
        return http.build()
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder? {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder()
    }

}