package black_and_while.service

import black_and_while.model.dto.CoffeeShopReviewDto
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

@Service
class CoffeeShopReviewService(
    private val coffeeShopReviewRepository: CoffeeShopReviewRepository,
    private val userRepository: UserRepository,
    private val coffeeShopRepository: CoffeeShopRepository,
) {

    @Transactional
    fun save(coffeeShopReviewDto: CoffeeShopReviewDto): CoffeeShopReview {
        val username: String = (SecurityContextHolder.getContext().authentication.principal as UserDetails).username
        val user: User = userRepository.findByLogin(username) ?: throw UsernameNotFoundException("User not found")
        val coffeeShopReview = CoffeeShopReview(
            coffeeShopId = coffeeShopReviewDto.coffeeShopId,
            review = coffeeShopReviewDto.review,
            score = coffeeShopReviewDto.score,
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