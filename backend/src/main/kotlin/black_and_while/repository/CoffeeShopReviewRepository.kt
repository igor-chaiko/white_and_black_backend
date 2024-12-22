package black_and_while.repository

import black_and_while.model.entity.CoffeeShopReview
import org.springframework.data.repository.CrudRepository

interface CoffeeShopReviewRepository : CrudRepository<CoffeeShopReview, Long> {
    fun getByCoffeeShopId(coffeeShopId: Long): List<CoffeeShopReview>
}