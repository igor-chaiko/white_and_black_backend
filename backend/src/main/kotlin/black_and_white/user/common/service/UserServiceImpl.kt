package black_and_white.user.common.service

import black_and_white.user.common.model.entity.User
import black_and_white.user.common.repository.UserRepository
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val authManager: AuthenticationManager,
) : UserService {
    override fun saveUser(user: User): Int =
        userRepository.saveUserWithUniqueLogin(user.login, passwordEncoder.encode(user.password), user.email)

    override fun changePassword(oldPassword: String, newPassword: String, newPasswordRepeat: String) {
        if (newPassword != newPasswordRepeat) {
            throw BadCredentialsException("Напишите новый пароль дважды")
        }

        val username: String = (SecurityContextHolder.getContext().authentication.principal as UserDetails).username

        authManager.authenticate(
            UsernamePasswordAuthenticationToken(
                username,
                oldPassword
            )
        )

        val user = userRepository.findByLogin(username) ?: throw BadCredentialsException("User not found")
        user.password = passwordEncoder.encode(newPassword)

        userRepository.save(user)
    }
}
