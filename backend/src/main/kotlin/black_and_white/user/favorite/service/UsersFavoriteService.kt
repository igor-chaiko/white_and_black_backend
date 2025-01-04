package black_and_white.user.favorite.service

import black_and_white.user.favorite.model.dto.response.CoffeeShopFavoriteDto
import black_and_white.user.favorite.model.dto.response.DrinkFavoriteDto

interface UsersFavoriteService {
    /**
     * Добавить/удалить избранное.
     */
    fun like(entityId: Long, entityType: String)

    /**
     * Получить все кофейни из избранного пользователя.
     */
    fun getFavoriteCoffeeShops(): List<CoffeeShopFavoriteDto>

    /**
     * Получить все напитки из избранного пользователя.
     */
    fun getFavoriteDrinks(): List<DrinkFavoriteDto>
}
