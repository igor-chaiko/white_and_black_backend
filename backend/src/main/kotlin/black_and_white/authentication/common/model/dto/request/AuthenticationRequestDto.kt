package black_and_white.authentication.common.model.dto.request

import jakarta.validation.constraints.Size

data class AuthenticationRequestDto(
    @field:Size(min = 4, message = "Длина имени пользователя должна быть не менее 4 символов")
    val login: String,
    @field:Size(min = 8, message = "Пароль должен содержать не менее 8 символов")
    val password: String,
)
