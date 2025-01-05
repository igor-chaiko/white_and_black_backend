package black_and_white.product.review.model.dto.request

data class ProductReviewRequestDto(
    val productId: Long,
    val review: String,
    val score: Int,
)