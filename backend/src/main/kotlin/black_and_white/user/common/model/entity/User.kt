package black_and_white.user.common.model.entity

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.Size
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("users")
data class User(
    @Id var id: Long? = null,
    val login: String,
    @Size(min = 7, message = "Пароль должен содержать не менее 7 символов")
    val password: String,
    @Email
    val email: String,
)
