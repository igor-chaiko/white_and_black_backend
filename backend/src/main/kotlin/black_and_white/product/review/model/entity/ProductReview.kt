package black_and_white.product.review.model.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.time.Instant
import java.time.ZoneId

@Table("product_reviews")
data class ProductReview(
    @Id var id: Long? = null,
    val productId: Long,
    val review: String,
    val score: Int,
    val userId: Long,
    var createdAt: Instant = Instant.now().atZone(ZoneId.of("Europe/Moscow")).toInstant(),
)
