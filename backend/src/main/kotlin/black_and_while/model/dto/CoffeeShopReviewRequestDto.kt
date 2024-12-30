package black_and_while.model.dto

import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.Positive

data class CoffeeShopReviewRequestDto(
    @field:Positive
    val coffeeShopId: Long,
    val review: String,
    @field:Max(value = 5, message = "Оценка должна быть в диапазоне от 0 до 5")
    @field:Min(value = 0, message = "Оценка должна быть в диапазоне от 0 до 5")
    val score: Int,
)