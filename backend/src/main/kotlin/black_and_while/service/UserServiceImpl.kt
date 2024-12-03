package black_and_while.service

import black_and_while.model.entity.User
import black_and_while.repository.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder
) : UserService {
    override fun saveUser(user: User): Int =
        userRepository.saveUserWithUniqueLogin(user.login, passwordEncoder.encode(user.password), user.email)
}
