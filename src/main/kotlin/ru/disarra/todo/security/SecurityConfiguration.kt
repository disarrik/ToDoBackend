package ru.disarra.todo.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpStatus
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.HttpStatusEntryPoint
import org.springframework.security.web.util.matcher.AntPathRequestMatcher
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource


@Configuration
class SecurityConfiguration {

    @Bean
    @Throws(Exception::class)
    fun filterChain(http: HttpSecurity): SecurityFilterChain? {
        http
            .csrf().disable()
            .cors().configurationSource(corsConfigurationSource())
            .and()
            .authorizeHttpRequests()
            .requestMatchers("/register")
            .permitAll()
            .requestMatchers("/login")
            .permitAll()
            .requestMatchers("/task")
            .authenticated()
            .requestMatchers("/group")
            .authenticated()
            .requestMatchers("/**")
            .permitAll()
            .and()
            .formLogin()
            .loginPage("/")
            .loginProcessingUrl("/login")
            .successHandler { _, _, _ -> /* do nothing */ }
            .failureHandler { _, response, _ -> response.status = 401}
            .and()
            .exceptionHandling()
            .defaultAuthenticationEntryPointFor(
                HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED), AntPathRequestMatcher("/**")
            )
            .and()
            .httpBasic()
        return http.build()
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder? {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder()
    }

    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource? {
        val configuration = CorsConfiguration()
        configuration.addAllowedOrigin("http://localhost:3000")
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");
        configuration.allowCredentials = true
        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", configuration)
        return source
    }

}