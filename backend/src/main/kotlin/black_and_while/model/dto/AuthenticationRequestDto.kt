package black_and_while.model.dto

import javax.validation.constraints.Size

data class AuthenticationRequestDto(
    val login: String,
    @field:Size(min = 8, message = "Пароль должен содержать не менее 8 символов")
    val password: String
)
