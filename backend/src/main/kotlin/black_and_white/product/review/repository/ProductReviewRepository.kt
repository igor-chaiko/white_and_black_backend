package black_and_white.product.review.repository

import black_and_white.product.review.model.entity.ProductReview
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductReviewRepository : CrudRepository<ProductReview, Long> {
    fun findAllByProductId(id: Long): List<ProductReview>
}