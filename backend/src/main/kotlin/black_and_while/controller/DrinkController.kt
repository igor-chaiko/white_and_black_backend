package black_and_while.controller

import black_and_while.model.dto.DrinkReviewRequestDto
import black_and_while.service.DrinkService
import jakarta.validation.Valid
import jakarta.validation.constraints.Positive
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/drink")
class DrinkController(
    private val drinkService: DrinkService,
) {
    @GetMapping("/all")
    fun getAllDrinks() =
        drinkService.getAllDrinksShort()

    @PostMapping("/review")
    fun reviewDrink(@RequestBody @Valid drinkReviewRequestDto: DrinkReviewRequestDto) =
        drinkService.reviewDrink(drinkReviewRequestDto)

    @GetMapping("/fullInfo/{id}")
    fun getDrink(@PathVariable id: Long) =
        drinkService.getById(id)

    @GetMapping("/reviews/{id}")
    fun getReviews(@PathVariable @Positive id: Long) =
        drinkService.getReviews(id)

    @GetMapping("/info/{id}")
    fun getShortInfo(@PathVariable @Positive id: Long) =
        drinkService.getShortInfo(id)
}