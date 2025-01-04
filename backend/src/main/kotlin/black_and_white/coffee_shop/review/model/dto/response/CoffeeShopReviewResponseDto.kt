package black_and_white.coffee_shop.review.model.dto.response

import java.time.Instant

data class CoffeeShopReviewResponseDto(
    val review: String,
    val score: Int,
    val user: String,
    val createdAt: Instant,
)
