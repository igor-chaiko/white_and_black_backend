package black_and_white.coffee_shop.review.repository

import black_and_white.coffee_shop.review.model.dto.response.CoffeeShopReviewResponseDto
import black_and_white.coffee_shop.review.model.entity.CoffeeShopReview
import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.CrudRepository

interface CoffeeShopReviewRepository : CrudRepository<CoffeeShopReview, Long> {
    @Query(
        """SELECT coffee_shop_reviews.review as review,
                  coffee_shop_reviews.score as score, 
                  users.login as "user",
                  coffee_shop_reviews.created_at as created_at
           from coffee_shop_reviews 
           join users on coffee_shop_reviews.user_id = users.id
           where coffee_shop_reviews.coffee_shop_id = :coffeeShopId"""
    )
    fun getByCoffeeShopId(coffeeShopId: Long): List<CoffeeShopReviewResponseDto>
}
