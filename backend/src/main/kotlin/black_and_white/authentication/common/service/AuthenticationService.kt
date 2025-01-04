package black_and_white.authentication.common.service

import black_and_white.authentication.common.model.dto.request.AuthenticationRequestDto
import black_and_white.authentication.common.model.dto.response.AuthenticationResponseDto

interface AuthenticationService {
    fun authentication(authenticationRequest: AuthenticationRequestDto): AuthenticationResponseDto

    fun updateAccessToken(refreshToken: String): String
}