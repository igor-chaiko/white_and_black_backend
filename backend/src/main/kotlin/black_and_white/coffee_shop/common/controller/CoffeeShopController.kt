package black_and_white.coffee_shop.common.controller

import black_and_white.coffee_shop.review.model.dto.request.CoffeeShopReviewRequestDto
import black_and_white.coffee_shop.common.model.dto.response.CoffeeShopShortDto
import black_and_white.coffee_shop.common.service.CoffeeShopService
import black_and_white.coffee_shop.review.service.CoffeeShopReviewService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/coffeeShop")
class CoffeeShopController(
    val coffeeShopService: CoffeeShopService,
    val coffeeShopReviewService: CoffeeShopReviewService,
) {
    @GetMapping("/all")
    fun getAllCoffeeShops() : List<CoffeeShopShortDto> =
        coffeeShopService.getAllShort()

    @GetMapping("/fullInfo/{id}")
    fun getFullCoffeeShopById(@PathVariable id: Long) =
        coffeeShopService.getById(id)

    @PostMapping("/review")
    fun saveCoffeeShopReview(@RequestBody @Valid review: CoffeeShopReviewRequestDto) =
        coffeeShopReviewService.save(review)

    @GetMapping("/info/{id}")
    fun getCoffeeShopInfoById(@PathVariable id: Long) =
        coffeeShopService.getInfoById(id)

    @GetMapping("/drinks/{id}")
    fun getDrinksById(@PathVariable id: Long) =
        coffeeShopService.getDrinksById(id)

    @GetMapping("/reviews/{id}")
    fun getReviewsById(@PathVariable id: Long) =
        coffeeShopReviewService.getReviewsByCoffeeShopId(id)

    @GetMapping("/products/{id}")
    fun getProductsById(@PathVariable id: Long) =
        coffeeShopService.getProductsById(id)
}
