package black_and_white.drink.common.service

import black_and_white.drink.common.converter.convertToInfoDto
import black_and_white.drink.common.converter.convertToShortDto
import black_and_white.drink.common.model.dto.response.DrinkInfoDto
import black_and_white.drink.common.model.dto.response.DrinkShortDto
import black_and_white.drink.review.repository.DrinkReviewRepository
import black_and_white.drink.common.repository.DrinkRepository
import black_and_white.user.common.repository.UserRepository
import black_and_white.user.favorite.model.enumeration.EntityToLike
import black_and_white.user.favorite.repository.UsersFavoriteRepository
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service

@Service
class DrinkServiceImpl(
    private val drinkRepository: DrinkRepository,
    private val usersFavoriteRepository: UsersFavoriteRepository,
    private val userRepository: UserRepository,
) : DrinkService {
    override fun getById(id: Long) =
        drinkRepository.findById(id)

    override fun getShortInfo(id: Long) : DrinkInfoDto {
        val fullDrink = drinkRepository.findById(id).orElseThrow {
            NoSuchElementException("Drink not found")
        }

        return fullDrink.convertToInfoDto(isDrinkFavorite(fullDrink.id!!))
    }

    override fun getAllDrinksShort() : List<DrinkShortDto> =
        drinkRepository.findAll().map { it.convertToShortDto(isDrinkFavorite(it.id!!)) }

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
