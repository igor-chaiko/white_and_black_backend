package black_and_white.coffee_shop.common.service

import black_and_white.coffee_shop.common.converter.convertToInfoDto
import black_and_white.coffee_shop.common.converter.convertToShortDto
import black_and_white.coffee_shop.common.model.dto.response.CoffeeShopInfoDto
import black_and_white.coffee_shop.common.model.dto.response.CoffeeShopShortDto
import black_and_white.drink.common.model.dto.response.DrinkShortDto
import black_and_white.coffee_shop.common.model.entity.CoffeeShop
import black_and_white.coffee_shop.common.repository.CoffeeShopRepository
import black_and_white.drink.common.converter.convertToShortDto
import black_and_white.drink.common.repository.DrinkRepository
import black_and_white.user.common.repository.UserRepository
import black_and_white.user.favorite.model.enumeration.EntityToLike
import black_and_white.user.favorite.repository.UsersFavoriteRepository
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service

@Service
class CoffeeShopServiceImpl(
    private val coffeeShopRepository: CoffeeShopRepository,
    private val drinkRepository: DrinkRepository,
    private val usersFavoriteRepository: UsersFavoriteRepository,
    private val userRepository: UserRepository,
) : CoffeeShopService {
    override fun getAllShort() : List<CoffeeShopShortDto> =
        coffeeShopRepository.findAll().map { it.convertToShortDto(isCoffeeShopFavorite(it.id!!)) }

    override fun getById(id: Long): CoffeeShop =
        coffeeShopRepository.findById(id).orElseThrow{ NoSuchElementException() }

    override fun getDrinksById(coffeeShopId: Long) : List<DrinkShortDto> {
        val fullDrinks = drinkRepository.getByCoffeeShopId(coffeeShopId).ifEmpty {
            throw NoSuchElementException()
        }

        return fullDrinks.map { it.convertToShortDto(isDrinkFavorite(it.id!!)) }
    }

    override fun getInfoById(coffeeShopId: Long) : CoffeeShopInfoDto {
        val isFavorite = isCoffeeShopFavorite(coffeeShopId)
        return getById(coffeeShopId).convertToInfoDto(isFavorite)
    }

    private fun isCoffeeShopFavorite(coffeeShopId: Long): Boolean {
        val userId = getUserId()
        return usersFavoriteRepository.existsByUserIdAndEntityIdAndEntityType(
            userId,
            coffeeShopId,
            EntityToLike.COFFEE_SHOP
        )
    }

    private fun isDrinkFavorite(drinkId: Long): Boolean {
        val userId = getUserId()
        return usersFavoriteRepository.existsByUserIdAndEntityIdAndEntityType(
            userId,
            drinkId,
            EntityToLike.DRINK
        )
    }

    private fun getUserId(): Long {
        val username = getUserLogin()
        val user = userRepository.findByLogin(username) ?: throw NoSuchElementException()
        return user.id!!
    }

    private fun getUserLogin() = (SecurityContextHolder.getContext().authentication.principal as UserDetails).username
}
