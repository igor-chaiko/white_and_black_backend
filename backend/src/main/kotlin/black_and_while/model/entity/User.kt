package black_and_while.model.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import javax.validation.constraints.Size

@Table(
    name = "users"
)
data class User(
    @Id var id: Long? = null,
    val login: String,
    @field:Size(min = 7, message = "Пароль должен содержать не менее 7 символов")
    val password: String,
    val email: String,
)
