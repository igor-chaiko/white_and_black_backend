package black_and_while.model.entity

import black_and_while.model.entity.enumeration.EntityToLike
import org.springframework.data.annotation.Id

data class UsersFavorite(
    @Id var id: Long? = null,
    val entityId: Long,
    val entityType: EntityToLike,
)
