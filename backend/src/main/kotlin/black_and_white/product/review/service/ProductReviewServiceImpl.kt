package black_and_white.product.review.service

import black_and_white.product.common.repository.ProductRepository
import black_and_white.product.review.model.dto.request.ProductReviewRequestDto
import black_and_white.product.review.model.entity.ProductReview
import black_and_white.product.review.repository.ProductReviewRepository
import black_and_white.user.common.model.entity.User
import black_and_white.user.common.repository.UserRepository
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ProductReviewServiceImpl(
    private val productReviewRepository: ProductReviewRepository,
    private val userRepository: UserRepository,
    private val productRepository: ProductRepository,
) : ProductReviewService {
    @Transactional
    override fun saveReview(productReviewRequestDto: ProductReviewRequestDto) {
        val username: String = (SecurityContextHolder.getContext().authentication.principal as UserDetails).username
        val user: User = userRepository.findByLogin(username) ?: throw UsernameNotFoundException("User not found")
        val productReview = ProductReview(
            productId = productReviewRequestDto.productId,
            review = productReviewRequestDto.review,
            score = productReviewRequestDto.score,
            userId = user.id!!
        )
        productReviewRepository.save(productReview)

        val reviewedProduct = productRepository.findById(productReview.productId).get()
        reviewedProduct.scoreCount++
        reviewedProduct.scoreCount += productReviewRequestDto.score
        productReviewRepository.save(productReview)
    }
}