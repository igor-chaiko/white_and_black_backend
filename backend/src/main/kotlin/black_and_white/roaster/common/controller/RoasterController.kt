package black_and_white.roaster.common.controller

import black_and_white.roaster.common.model.dto.response.RoasterShortDto
import black_and_white.roaster.common.model.entity.Roaster
import black_and_white.roaster.common.repository.RoasterRepository
import black_and_white.roaster.common.service.RoasterService
import black_and_white.roaster.review.model.dto.request.RoasterReviewRequestDto
import black_and_white.roaster.review.model.entity.RoasterReview
import black_and_white.roaster.review.repository.RoasterReviewRepository
import black_and_white.roaster.review.service.RoasterReviewService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/roaster")
class RoasterController(
    private val roasterService: RoasterService,
    private val roasterRepository: RoasterRepository,
    private val roasterReviewService: RoasterReviewService,
    private val roasterReviewRepository: RoasterReviewRepository,
) {
    @GetMapping("/all")
    fun getAll() : List<RoasterShortDto> =
        roasterService.getAllShort()

    @GetMapping("/fullInfo/{id}")
    fun getFullRoasterById(@PathVariable id: Long): Roaster =
        roasterRepository.findById(id).orElseThrow{ NoSuchElementException("Такого обжарщика нет") }

    @PostMapping("/review")
    fun saveReview(@RequestBody roasterReviewDto: RoasterReviewRequestDto): RoasterReview =
        roasterReviewService.save(roasterReviewDto)


    @GetMapping("/info/{id}")
    fun getRoasterInfoById(@PathVariable id: Long) =
        roasterService.getInfoById(id)

//    @GetMapping("/products/{id}")

    @GetMapping("/reviews/{id}")
    fun getReviewsById(@PathVariable id: Long) =
        roasterReviewRepository.getReviewsByRoasterId(id)
}