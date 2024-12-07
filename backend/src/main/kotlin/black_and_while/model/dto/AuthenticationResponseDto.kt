package black_and_while.model.dto

data class AuthenticationResponseDto(
    val accessToken: String,
    val refreshToken: String
)
