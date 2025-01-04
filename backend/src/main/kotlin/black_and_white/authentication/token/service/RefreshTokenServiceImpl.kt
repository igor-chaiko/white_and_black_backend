package black_and_white.authentication.token.service

import black_and_white.authentication.token.model.entity.RefreshToken
import black_and_white.user.common.model.entity.User
import black_and_white.authentication.token.repository.RefreshTokenRepository
import black_and_white.user.common.repository.UserRepository
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class RefreshTokenServiceImpl(
    private val refreshTokenRepository: RefreshTokenRepository,
    private val userRepository: UserRepository
) : RefreshTokenService {
    override fun save(token: String, user: UserDetails) {
        val tokenToSave = RefreshToken(
            token = token,
            username = user.username
        )
        refreshTokenRepository.save(tokenToSave)
    }

    override fun findUserDetailsByToken(token: String): UserDetails {
        val entity: RefreshToken = refreshTokenRepository.findByToken(token)
            ?: throw UsernameNotFoundException("User not found")
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
