package black_and_white.product.common.controller

import black_and_white.product.common.converter.convertToDisplayDto
import black_and_white.product.common.converter.convertToInfoDto
import black_and_white.product.common.repository.ProductRepository
import black_and_white.product.common.service.ProductService
import black_and_white.product.review.model.dto.request.ProductReviewRequestDto
import black_and_white.product.review.repository.ProductReviewRepository
import black_and_white.product.review.service.ProductReviewService
import jakarta.validation.Valid
import jakarta.validation.constraints.Positive
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/product")
class ProductController(
    private val productService: ProductService,
    private val productRepository: ProductRepository,
    private val productReviewService: ProductReviewService,
    private val productReviewRepository: ProductReviewRepository,
) {
    @GetMapping("/all")
    fun getAllProductsShort() =
        productRepository.findAll().map { it.convertToDisplayDto() }

    @PostMapping("/review")
    fun reviewDrink(@RequestBody @Valid productReviewRequestDto: ProductReviewRequestDto) =
        productReviewService.saveReview(productReviewRequestDto)

    @GetMapping("/fullInfo/{id}")
    fun getDrink(@PathVariable id: Long) =
        productRepository.findById(id).orElseThrow{ NoSuchElementException("Продукт с id ${id} не найден") }

    @GetMapping("/reviews/{id}")
    fun getReviews(@PathVariable @Positive id: Long) =
        productReviewRepository.findAllByProductId(id)

    @GetMapping("/info/{id}")
    fun getShortInfo(@PathVariable @Positive id: Long) =
        productService.findInfoById(id)
}
