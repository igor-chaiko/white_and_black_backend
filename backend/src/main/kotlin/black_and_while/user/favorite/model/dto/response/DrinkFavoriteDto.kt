package black_and_while.user.favorite.model.dto.response

import black_and_while.model.entity.enumeration.DrinkTemperature
import black_and_while.model.entity.enumeration.DrinkType

data class DrinkFavoriteDto(
    val id: Long,
    val name: String,
    val type: DrinkType,
    val temperature: DrinkTemperature,
    val score: Float,
)
