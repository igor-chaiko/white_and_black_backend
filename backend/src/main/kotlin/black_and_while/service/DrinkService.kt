package black_and_while.service

import black_and_while.model.dto.DrinkInfoDto
import black_and_while.model.dto.DrinkReviewRequestDto
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
    fun reviewDrink(drinkReviewRequestDto: DrinkReviewRequestDto) : DrinkReview {
        val username: String = (SecurityContextHolder.getContext().authentication.principal as UserDetails).username
        val user: User = userRepository.findByLogin(username) ?: throw UsernameNotFoundException("User not found")
        val drinkReview = DrinkReview(
            drinkId = drinkReviewRequestDto.drinkId,
            review = drinkReviewRequestDto.review,
            score = drinkReviewRequestDto.score,
            userId = user.id!!
        )
        val savedDrinkReview = drinkReviewRepository.save(drinkReview)
        val reviewedDrink = drinksRepository.findById(drinkReviewRequestDto.drinkId).orElseThrow {
            NoSuchElementException("Drink not found") }
        reviewedDrink.scoreSum += drinkReviewRequestDto.score
        reviewedDrink.scoreCount++
        drinksRepository.save(reviewedDrink)
        return savedDrinkReview
    }

    fun getById(id: Long) =
        drinksRepository.findById(id)

    fun getReviews(id: Long) =
        drinkReviewRepository.findAllByDrinkId(id)

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