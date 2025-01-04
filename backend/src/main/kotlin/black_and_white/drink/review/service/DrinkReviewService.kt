package black_and_white.drink.review.service

import black_and_white.drink.review.model.dto.request.DrinkReviewRequestDto
import black_and_white.drink.review.model.dto.response.DrinkReviewResponseDto
import black_and_white.drink.review.model.entity.DrinkReview
import org.springframework.stereotype.Service

interface DrinkReviewService {
    fun saveReview(drinkReviewRequestDto: DrinkReviewRequestDto) : DrinkReview

    fun getReviews(id: Long) : List<DrinkReviewResponseDto>
}