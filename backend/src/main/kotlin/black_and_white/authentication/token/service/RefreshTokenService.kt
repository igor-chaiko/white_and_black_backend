package black_and_white.authentication.token.service

import org.springframework.security.core.userdetails.UserDetails

interface RefreshTokenService {
    fun save(token: String, user: UserDetails)

    fun findUserDetailsByToken(token: String): UserDetails
}