package black_and_while.service

import black_and_while.model.dto.DrinkInfoDto
import black_and_while.model.dto.DrinkReviewDto
import black_and_while.model.entity.DrinkReview
import black_and_while.model.entity.User
import black_and_while.repository.DrinkReviewRepository
import black_and_while.repository.DrinksRepository
import black_and_while.repository.UserRepository
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class DrinkService(
    private val drinksRepository: DrinksRepository,
    private val drinkReviewRepository: DrinkReviewRepository,
    private val userRepository: UserRepository,
) {
    @Transactional
    fun reviewDrink(drinkReviewDto: DrinkReviewDto) : DrinkReview {
        val username: String = (SecurityContextHolder.getContext().authentication.principal as UserDetails).username
        val user: User = userRepository.findByLogin(username) ?: throw UsernameNotFoundException("User not found")
        val drinkReview = DrinkReview(
            drinkId = drinkReviewDto.drinkId,
            review = drinkReviewDto.review,
            score = drinkReviewDto.score,
            userId = user.id!!
        )
        val savedDrinkReview = drinkReviewRepository.save(drinkReview)
        val reviewedDrink = drinksRepository.findById(drinkReviewDto.drinkId).orElseThrow {
            NoSuchElementException("Drink not found") }
        reviewedDrink.scoreSum += drinkReviewDto.score
        reviewedDrink.scoreCount++
        drinksRepository.save(reviewedDrink)
        return savedDrinkReview
    }

    fun getById(id: Long) =
        drinksRepository.findById(id)

    fun getReviews(id: Long) =
        drinkReviewRepository.findAllByDrinkId(id).ifEmpty {
            throw NoSuchElementException("Drink not found")
        }

    fun getShortInfo(id: Long) : DrinkInfoDto {
        val fullDrink = drinksRepository.findById(id).orElseThrow {
            NoSuchElementException("Drink not found")
        }
        return DrinkInfoDto(
            id = fullDrink.id!!,
            name = fullDrink.name,
            type = fullDrink.type,
            temperature = fullDrink.temperature,
            coffeeShop = fullDrink.coffeeShop,
            score = if (fullDrink.scoreCount == 0) 0f else (fullDrink.scoreSum.toFloat() / fullDrink.scoreCount),
            composition = fullDrink.composition
        )
    }
}