package black_and_while.model.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.time.OffsetDateTime

@Table("drink_reviews")
data class DrinkReview(
    @Id var id: Long? = null,
    val drinkId: Long,
    val review: String,
    val score: Int,
    val userId: Long,
    var createdAt: OffsetDateTime? = null,
)
