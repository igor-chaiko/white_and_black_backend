package black_and_white.coffee_shop.review.service

import black_and_white.coffee_shop.review.model.dto.request.CoffeeShopReviewRequestDto
import black_and_white.coffee_shop.review.model.dto.response.CoffeeShopReviewResponseDto
import black_and_white.coffee_shop.review.model.entity.CoffeeShopReview

interface CoffeeShopReviewService {
    fun save(coffeeShopReviewRequestDto: CoffeeShopReviewRequestDto): CoffeeShopReview

    fun getReviewsByCoffeeShopId(coffeeShopId: Long): List<CoffeeShopReviewResponseDto>
}