package black_and_while.model.dto

import java.time.Instant

data class CoffeeShopReviewResponseDto(
    val review: String,
    val score: Int,
    val user: String,
    val createdAt: Instant,
)
