package black_and_while.model.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.time.OffsetDateTime

@Table(name = "coffee_shop_reviews")
data class CoffeeShopReview(
    @Id var id: Long? = null,
    val coffeeShopId: Long,
    val review: String,
    val score: Int,
    val userId: Long,
    var createdAt: OffsetDateTime? = null,
)