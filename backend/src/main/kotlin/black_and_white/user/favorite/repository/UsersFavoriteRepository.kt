package black_and_white.user.favorite.repository

import black_and_white.user.favorite.model.entity.UsersFavorite
import black_and_white.user.favorite.model.enumeration.EntityToLike
import org.springframework.data.repository.CrudRepository

interface UsersFavoriteRepository : CrudRepository<UsersFavorite, Long> {
    fun findByUserIdAndEntityIdAndEntityType(userId: Long, entityId: Long, entityType: EntityToLike): UsersFavorite?

    fun findAllByUserIdAndEntityType(userId: Long, entityType: EntityToLike): List<UsersFavorite>

    fun existsByUserIdAndEntityIdAndEntityType(userId: Long, entityId: Long, entityType: EntityToLike): Boolean
}
