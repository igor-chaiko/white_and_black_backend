package black_and_while.service

import black_and_while.model.dto.CoffeeShopReviewRequestDto
import black_and_while.model.dto.CoffeeShopReviewResponseDto
import black_and_while.model.entity.CoffeeShopReview
import black_and_while.model.entity.User
import black_and_while.repository.CoffeeShopRepository
import black_and_while.repository.CoffeeShopReviewRepository
import black_and_while.repository.UserRepository
import org.slf4j.LoggerFactory
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.ZoneId

@Service
class CoffeeShopReviewService(
    private val coffeeShopReviewRepository: CoffeeShopReviewRepository,
    private val userRepository: UserRepository,
    private val coffeeShopRepository: CoffeeShopRepository,
) {

    @Transactional
    fun save(coffeeShopReviewRequestDto: CoffeeShopReviewRequestDto): CoffeeShopReview {
        val username: String = (SecurityContextHolder.getContext().authentication.principal as UserDetails).username
        val user: User = userRepository.findByLogin(username) ?: throw UsernameNotFoundException("User not found")
        val coffeeShopReview = CoffeeShopReview(
            coffeeShopId = coffeeShopReviewRequestDto.coffeeShopId,
            review = coffeeShopReviewRequestDto.review,
            score = coffeeShopReviewRequestDto.score,
            userId = user.id!!
        )

        val savedCoffeeShopReview = coffeeShopReviewRepository.save(coffeeShopReview)
        val reviewedCoffeeShop = coffeeShopRepository.findById(coffeeShopReview.coffeeShopId).get()
        reviewedCoffeeShop.scoreCount++
        reviewedCoffeeShop.scoreSum += coffeeShopReview.score
        coffeeShopRepository.save(reviewedCoffeeShop)
        return savedCoffeeShopReview

    }

    fun getReviewsByCoffeeShopId(coffeeShopId: Long) =
        coffeeShopReviewRepository.getByCoffeeShopId(coffeeShopId)
}