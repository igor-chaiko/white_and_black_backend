package black_and_while.service

import black_and_while.model.entity.User
import black_and_while.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
) : UserService {
    override fun saveUser(user: User): Int =
        userRepository.saveUserWithUniqueLogin(user.login, user.password, user.email)
}
