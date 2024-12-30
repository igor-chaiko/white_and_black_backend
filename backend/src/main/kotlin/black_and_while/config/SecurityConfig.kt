package black_and_while.config

import black_and_while.filter.JwtFilter
import black_and_while.service.JwtUserDetailsService
import black_and_while.user.common.service.UserServiceImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpStatus
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.DefaultSecurityFilterChain
import org.springframework.security.web.authentication.HttpStatusEntryPoint
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.cors.CorsConfiguration

@Configuration
@EnableWebSecurity
class SecurityConfig(
    val jwtUserDetailsService: JwtUserDetailsService
) {

    @Bean
    fun filterChain(http: HttpSecurity, jwtFilter: JwtFilter): DefaultSecurityFilterChain {
        http
            .cors {corsCustomizer ->
                corsCustomizer.configurationSource {
                    CorsConfiguration().apply {
                        allowedOrigins = listOf() // Пустой список отключает CORS
                        allowedMethods = listOf() // Пустой список отключает методы
                        allowedHeaders = listOf() // Пустой список отключает заголовки
                    }
                }
            }
            .csrf { it.disable() }
            .authorizeHttpRequests {
                it
                    .requestMatchers("/auth/refresh").authenticated()
                    .requestMatchers("/auth/**").permitAll()
                    .anyRequest().authenticated()
            }
            .sessionManagement {
                it.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            }
            .exceptionHandling{
                it.authenticationEntryPoint(HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
            }
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter::class.java)

        return http.build()
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()

    @Bean
    fun authenticationManager(authenticationConfiguration: AuthenticationConfiguration): AuthenticationManager =
        authenticationConfiguration.getAuthenticationManager()

    @Bean
    fun daoAuthenticationProvider(userServiceImpl: UserServiceImpl): DaoAuthenticationProvider
        = DaoAuthenticationProvider().also {
            it.setPasswordEncoder(passwordEncoder())
            it.setUserDetailsService(jwtUserDetailsService)
        }

}
