package black_and_while.user.favorite.service

import black_and_while.coffe_shop.common.repository.CoffeeShopRepository
import black_and_while.model.dto.favorites.response.FavoriteDto
import black_and_while.model.entity.UsersFavorite
import black_and_while.model.entity.enumeration.EntityToLike
import black_and_while.repository.DrinksRepository
import black_and_while.user.common.repository.UserRepository
import black_and_while.user.favorite.converter.convertToDto
import black_and_while.user.favorite.converter.convertToFavoriteEntity
import black_and_while.user.favorite.model.dto.response.CoffeeShopFavoriteDto
import black_and_while.user.favorite.model.dto.response.DrinkFavoriteDto
import black_and_while.user.favorite.repository.UsersFavoriteRepository
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class UsersFavoriteServiceImpl(
    private val userFavoriteRepository: UsersFavoriteRepository,
    private val userRepository: UserRepository,
    private val coffeeShopRepository: CoffeeShopRepository,
    private val drinksRepository: DrinksRepository,
) : UsersFavoriteService {
    override fun like(entityId: Long, entityType: String) {
        val user = userRepository.findByLogin(getUserLogin()) ?: throw UsernameNotFoundException("User not found")

        userFavoriteRepository.findByUserIdAndEntityId(user.id!!, entityId)?.let {
            userFavoriteRepository.delete(it)
            return
        }

        userFavoriteRepository.save(
            UsersFavorite(
                userId = user.id!!,
                entityId = entityId,
                entityType = entityType.convertToFavoriteEntity(),
            ),
        )
    }

    override fun getFavoriteCoffeeShops(): List<CoffeeShopFavoriteDto> {
        val user = userRepository.findByLogin(getUserLogin()) ?: throw UsernameNotFoundException("User not found")

        val favoriteCoffeeShopsIds =
            userFavoriteRepository.findAllByUserIdAndEntityType(user.id!!, EntityToLike.COFFEE_SHOP).map { it.entityId }

        return coffeeShopRepository.findAllByIdIn(favoriteCoffeeShopsIds).map { it.convertToDto() }
    }

    override fun getFavoriteDrinks(): List<DrinkFavoriteDto> {
        val user = userRepository.findByLogin(getUserLogin()) ?: throw UsernameNotFoundException("User not found")

        val favoriteDrinksIds =
            userFavoriteRepository.findAllByUserIdAndEntityType(user.id!!, EntityToLike.DRINK).map { it.entityId }

        return drinksRepository.findAllByIdIn(favoriteDrinksIds).map { it.convertToDto() }
    }

    private fun getUserLogin() = (SecurityContextHolder.getContext().authentication.principal as UserDetails).username
}
