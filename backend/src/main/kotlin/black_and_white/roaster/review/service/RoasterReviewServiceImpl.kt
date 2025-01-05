package black_and_white.roaster.review.service

import black_and_white.roaster.common.repository.RoasterRepository
import black_and_white.roaster.review.model.dto.request.RoasterReviewRequestDto
import black_and_white.roaster.review.model.entity.RoasterReview
import black_and_white.roaster.review.repository.RoasterReviewRepository
import black_and_white.user.common.model.entity.User
import black_and_white.user.common.repository.UserRepository
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class RoasterReviewServiceImpl(
    private val roasterReviewRepository: RoasterReviewRepository,
    private val userRepository: UserRepository,
    private val roasterRepository: RoasterRepository,
) : RoasterReviewService {

    @Transactional
    override fun save(roasterReviewRequest: RoasterReviewRequestDto): RoasterReview {
        val username: String = (SecurityContextHolder.getContext().authentication.principal as UserDetails).username
        val user: User = userRepository.findByLogin(username) ?: throw UsernameNotFoundException("User not found")
        val roasterReview = RoasterReview(
            roasterId = roasterReviewRequest.roasterId,
            review = roasterReviewRequest.review,
            score = roasterReviewRequest.score,
            userId = user.id!!
        )

        val savedRoasterReview = roasterReviewRepository.save(roasterReview)
        val reviewedRoaster = roasterRepository.findById(roasterReviewRequest.roasterId).get()
        reviewedRoaster.scoreSum++
        reviewedRoaster.scoreCount += roasterReviewRequest.score
        roasterRepository.save(reviewedRoaster)
        return savedRoasterReview
    }
}