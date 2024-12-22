package black_and_while.model.dto

import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.Positive

data class DrinkReviewDto(
    @field:Positive
    val drinkId: Long,
    val review: String,
    @field:Max(value = 5, message = "Оценка должна быть от 0 до 5")
    @field:Min(value = 0, message = "Оценка должна быть от 0 до 5")
    val score: Int,
)
