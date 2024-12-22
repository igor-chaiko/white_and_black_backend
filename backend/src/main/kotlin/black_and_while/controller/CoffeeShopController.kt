package black_and_while.controller

import black_and_while.model.dto.CoffeeShopReviewDto
import black_and_while.model.dto.CoffeeShopShortDto
import black_and_while.service.CoffeeShopReviewService
import black_and_while.service.CoffeeShopService
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
    fun saveCoffeeShopReview(@RequestBody @Valid review: CoffeeShopReviewDto) =
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

}