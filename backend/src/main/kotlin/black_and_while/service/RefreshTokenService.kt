package black_and_while.service

import black_and_while.model.entity.RefreshToken
import black_and_while.model.entity.User
import black_and_while.repository.RefreshTokenRepository
import black_and_while.repository.UserRepository
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class RefreshTokenService(
    private val refreshTokenRepository: RefreshTokenRepository,
    private val userRepository: UserRepository
) {
    fun save(token: String, user: UserDetails) {
        val tokenToSave = RefreshToken(
            token = token,
            username = user.username
        )
        refreshTokenRepository.save(tokenToSave)
    }

    fun findUserDetailsByToken(token: String): UserDetails {
        val entity: RefreshToken = refreshTokenRepository.findByToken(token)
        val username = entity.username
        val user: User = userRepository.findByLogin(username)
            ?: throw UsernameNotFoundException(("User $username not found!"))
        val userDetails: UserDetails = org.springframework.security.core.userdetails.User(
            user.login,
            user.password,
            emptyList<GrantedAuthority>()
        )
        return userDetails
    }



}