package black_and_white.roaster.review.model.dto.response

import java.time.Instant

data class RoasterReviewResponseDto(
    val review: String,
    val score: Int,
    val user: String,
    var createdAt: Instant
)