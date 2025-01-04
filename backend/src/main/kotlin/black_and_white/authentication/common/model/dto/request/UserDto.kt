package black_and_white.authentication.common.model.dto.request


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size

data class UserDto(
    @field:Size(min = 4, message = "Длина имени пользователя должна быть не менее 4 символов")
    val login: String,
    @field:Size(min = 8, message = "Длина пароля должна быть не менее 8 символов")
    val password: String,
    @field:Email
    val email: String,
)
