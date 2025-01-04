package black_and_white.drink.common.model.dto.response

import black_and_white.drink.common.model.enumeration.DrinkTemperature
import black_and_white.drink.common.model.enumeration.DrinkType

data class DrinkShortDto(
    val id: Long,
    val name: String,
    val type: DrinkType,
    val temperature: DrinkTemperature,
    val score: Float,
)
