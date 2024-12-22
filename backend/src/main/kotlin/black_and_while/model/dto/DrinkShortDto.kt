package black_and_while.model.dto

import black_and_while.model.entity.enumeration.DrinkTemperature
import black_and_while.model.entity.enumeration.DrinkType

data class DrinkShortDto(
    val id: Long,
    val name: String,
    val type: DrinkType,
    val temperature: DrinkTemperature,
    val score: Float,
)
