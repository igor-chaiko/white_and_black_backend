package black_and_white.coffee_shop.review.service

import black_and_white.coffee_shop.review.model.dto.request.CoffeeShopReviewRequestDto
import black_and_white.coffee_shop.review.model.entity.CoffeeShopReview
import black_and_white.user.common.model.entity.User
import black_and_white.coffee_shop.common.repository.CoffeeShopRepository
import black_and_white.coffee_shop.review.repository.CoffeeShopReviewRepository
import black_and_white.user.common.repository.UserRepository
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CoffeeShopReviewServiceImpl(
    private val coffeeShopReviewRepository: CoffeeShopReviewRepository,
    private val userRepository: UserRepository,
    private val coffeeShopRepository: CoffeeShopRepository,
) : CoffeeShopReviewService {

    @Transactional
    override fun save(coffeeShopReviewRequestDto: CoffeeShopReviewRequestDto): CoffeeShopReview {
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

    override fun getReviewsByCoffeeShopId(coffeeShopId: Long) =
        coffeeShopReviewRepository.getByCoffeeShopId(coffeeShopId)
}
