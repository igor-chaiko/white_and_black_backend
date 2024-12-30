package black_and_while.user.favorite.repository

import black_and_while.model.entity.UsersFavorite
import black_and_while.model.entity.enumeration.EntityToLike
import org.springframework.data.repository.CrudRepository

interface UsersFavoriteRepository : CrudRepository<UsersFavorite, Long> {
    fun findByUserIdAndEntityId(userId: Long, entityId: Long): UsersFavorite?

    fun findAllByUserIdAndEntityType(userId: Long, entityType: EntityToLike): List<UsersFavorite>
}
