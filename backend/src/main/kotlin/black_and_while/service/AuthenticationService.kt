package black_and_while.service

import black_and_while.model.dto.AuthenticationRequestDto
import black_and_while.model.dto.AuthenticationResponseDto
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.AuthenticationServiceException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import java.util.*

@Service
class AuthenticationService(
    private val authManager: AuthenticationManager,
    private val userDetailsService: UserDetailsService,
    private val tokenService: JwtService,
    private val refreshTokenService: RefreshTokenService,
    @Value("\${jwt.accessTokenExpiration}") private val accessTokenExpiration: Long,
    @Value("\${jwt.refreshTokenExpiration}") private val refreshTokenExpiration: Long,
) {
    fun authentication(authenticationRequest: AuthenticationRequestDto): AuthenticationResponseDto {
        authManager.authenticate(
            UsernamePasswordAuthenticationToken(
                authenticationRequest.login,
                authenticationRequest.password
            )
        )
        val user = userDetailsService.loadUserByUsername(authenticationRequest.login)

        val refreshToken = createRefreshToken(user)
        refreshTokenService.save(refreshToken, user)

        val accessToken = createAccessToken(user)

        return AuthenticationResponseDto(
            accessToken = accessToken,
            refreshToken = refreshToken
        )
    }

    fun updateAccessToken(refreshToken: String): String {
        val username = tokenService.getUsernameFromToken(refreshToken)

        return username.let { user ->
            val currentUserDetails = userDetailsService.loadUserByUsername(user)
            val refreshTokenUserDetails = refreshTokenService.findUserDetailsByToken(refreshToken)

            if (currentUserDetails.username == refreshTokenUserDetails.username)
                createAccessToken(currentUserDetails)
            else
                throw AuthenticationServiceException("Invalid refresh token")
        }
    }

    private fun createAccessToken(user: UserDetails): String =
        tokenService.generateToken(
            subject = user.username,
            expirationDate = Date(System.currentTimeMillis()
                    + accessTokenExpiration)
        )

    private fun createRefreshToken(user: UserDetails) =
        tokenService.generateToken(
            subject = user.username,
            expirationDate = Date(System.currentTimeMillis()
                    + refreshTokenExpiration)
    )
}