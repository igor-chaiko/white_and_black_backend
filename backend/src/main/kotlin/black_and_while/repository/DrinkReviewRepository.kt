package black_and_while.repository

import black_and_while.model.entity.DrinkReview
import org.springframework.data.repository.CrudRepository

interface DrinkReviewRepository : CrudRepository<DrinkReview, Long> {
    fun findAllByDrinkId(drinkId: Long): List<DrinkReview>
}