package black_and_white.user.common.service

import black_and_white.user.common.model.entity.User
import black_and_white.user.common.repository.UserRepository
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
