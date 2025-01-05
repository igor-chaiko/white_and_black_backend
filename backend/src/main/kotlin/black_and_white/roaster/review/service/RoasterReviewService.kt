package black_and_white.roaster.review.service

import black_and_white.roaster.review.model.dto.request.RoasterReviewRequestDto
import black_and_white.roaster.review.model.entity.RoasterReview

interface RoasterReviewService {
    fun save(roasterReviewRequest: RoasterReviewRequestDto): RoasterReview
}