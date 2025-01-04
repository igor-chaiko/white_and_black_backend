package black_and_white.drink.review.repository

import black_and_white.drink.review.model.dto.response.DrinkReviewResponseDto
import black_and_white.drink.review.model.entity.DrinkReview
import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.CrudRepository

interface DrinkReviewRepository : CrudRepository<DrinkReview, Long> {
    @Query(
        """SELECT drink_reviews.review as review,
                  drink_reviews.score as score,
                  users.login as "user",
                  drink_reviews.created_at as created_at
           from drink_reviews join users on drink_reviews.user_id = users.id
           where drink_reviews.drink_id = :drinkId"""
    )
    fun findAllByDrinkId(drinkId: Long): List<DrinkReviewResponseDto>
}