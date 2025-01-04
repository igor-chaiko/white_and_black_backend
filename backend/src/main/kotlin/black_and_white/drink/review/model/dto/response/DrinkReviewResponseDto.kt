package black_and_white.drink.review.model.dto.response

import java.time.Instant

data class DrinkReviewResponseDto (
    val review: String,
    val score: Int,
    val user: String,
    val createdAt: Instant,
)
