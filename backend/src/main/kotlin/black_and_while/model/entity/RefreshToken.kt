package black_and_while.model.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table(name = "refresh_tokens")
data class RefreshToken (
    @Id
    var id: Long? = null,
    var token: String,
    var username: String,
)