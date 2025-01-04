package black_and_white.drink.review.service

import black_and_white.drink.review.model.dto.request.DrinkReviewRequestDto
import black_and_white.drink.review.model.entity.DrinkReview
import black_and_white.user.common.model.entity.User
import black_and_white.drink.common.repository.DrinkRepository
import black_and_white.drink.review.repository.DrinkReviewRepository
import black_and_white.user.common.repository.UserRepository
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class DrinkReviewServiceImpl(
    private val userRepository: UserRepository,
    private val drinkReviewRepository: DrinkReviewRepository,
    private val drinkRepository: DrinkRepository,
) : DrinkReviewService {
    @Transactional
    override fun saveReview(drinkReviewRequestDto: DrinkReviewRequestDto): DrinkReview {
        val username: String = (SecurityContextHolder.getContext().authentication.principal as UserDetails).username
        val user: User = userRepository.findByLogin(username) ?: throw UsernameNotFoundException("User not found")
        val drinkReview = DrinkReview(
            drinkId = drinkReviewRequestDto.drinkId,
            review = drinkReviewRequestDto.review,
            score = drinkReviewRequestDto.score,
            userId = user.id!!
        )
        val savedDrinkReview = drinkReviewRepository.save(drinkReview)
        val reviewedDrink = drinkRepository.findById(drinkReviewRequestDto.drinkId).orElseThrow {
            NoSuchElementException("Drink not found") }
        reviewedDrink.scoreSum += drinkReviewRequestDto.score
        reviewedDrink.scoreCount++
        drinkRepository.save(reviewedDrink)
        return savedDrinkReview
    }

    override fun getReviews(id: Long) =
        drinkReviewRepository.findAllByDrinkId(id)
}
