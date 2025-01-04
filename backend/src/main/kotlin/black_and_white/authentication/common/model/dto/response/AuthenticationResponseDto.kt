package black_and_white.authentication.common.model.dto.response

data class AuthenticationResponseDto(
    val accessToken: String,
    val refreshToken: String
)
