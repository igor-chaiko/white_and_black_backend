package black_and_while.user.favorite.service

import black_and_while.model.dto.favorites.response.FavoriteDto

interface UsersFavoriteService {
    /**
     * Добавить/удалить избранное.
     */
    fun like(entityId: Long, entityType: String)

    /**
     * Получить список избранного.
     */
    fun getListByEntityType(entityType: String): FavoriteDto
}
