package black_and_white.roaster.review.model.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.time.Instant
import java.time.ZoneId

@Table(name = "roaster_reviews")
data class RoasterReview(
    @Id var id: Long? = null,
    val roasterId: Long,
    val review: String,
    val score: Int,
    val userId: Long,
    var createdAt: Instant = Instant.now().atZone(ZoneId.of("Europe/Moscow")).toInstant(),
)