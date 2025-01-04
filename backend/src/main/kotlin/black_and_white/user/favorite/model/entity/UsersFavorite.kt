package black_and_white.user.favorite.model.entity

import black_and_white.user.favorite.model.enumeration.EntityToLike
import org.springframework.data.annotation.Id

data class UsersFavorite(
    @Id var id: Long? = null,
    val userId: Long,
    val entityId: Long,
    val entityType: EntityToLike,
)
