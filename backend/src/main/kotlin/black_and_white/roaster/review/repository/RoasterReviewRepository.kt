package black_and_white.roaster.review.repository

import black_and_white.roaster.review.model.dto.response.RoasterReviewResponseDto
import black_and_white.roaster.review.model.entity.RoasterReview
import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface RoasterReviewRepository : CrudRepository<RoasterReview, Long> {
    @Query(
        """SELECT roaster_reviews.review as review,
                  roaster_reviews.score as score, 
                  users.login as "user",
                  roaster_reviews.created_at as createdAt
           from roaster_reviews 
           join users on roaster_reviews.user_id = users.id
           where roaster_reviews.roaster_id = :roasterId"""
    )
    fun getReviewsByRoasterId(roasterId: Long): List<RoasterReviewResponseDto>
}