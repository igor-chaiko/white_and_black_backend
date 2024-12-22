package black_and_while.model.dto.auth

data class AuthenticationResponseDto(
    val accessToken: String,
    val refreshToken: String
)
