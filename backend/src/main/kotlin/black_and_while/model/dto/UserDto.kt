package black_and_while.model.dto

import javax.validation.constraints.Email

data class UserDto(
    val login: String,
    val password: String,
    @Email
    val email: String,
)
