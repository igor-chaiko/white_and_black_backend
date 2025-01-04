package black_and_white.user.common.model.dto.request

import jakarta.validation.constraints.Size

data class PasswordChangeDto(
    @field:Size(min = 8, message = "Пароль должен содержать не менее 8 символов")
    val oldPassword: String,
    @field:Size(min = 8, message = "Новый пароль должен содержать не менее 8 символов")
    val newPassword: String,
    @field:Size(min = 8, message = "Новый пароль должен содержать не менее 8 символов")
    val newPasswordRepeat: String,
)
