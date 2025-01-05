package black_and_white.product.review.service

import black_and_white.product.review.model.dto.request.ProductReviewRequestDto

interface ProductReviewService {
    fun saveReview(productReviewRequestDto: ProductReviewRequestDto)
}