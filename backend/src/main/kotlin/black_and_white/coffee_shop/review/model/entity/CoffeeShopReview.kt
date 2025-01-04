package black_and_white.coffee_shop.review.model.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.time.Instant
import java.time.ZoneId

@Table(name = "coffee_shop_reviews")
data class CoffeeShopReview(
    @Id var id: Long? = null,
    val coffeeShopId: Long,
    val review: String,
    val score: Int,
    val userId: Long,
    var createdAt: Instant = Instant.now().atZone(ZoneId.of("Europe/Moscow")).toInstant(),
)
