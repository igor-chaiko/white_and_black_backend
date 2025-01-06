package black_and_white.drink.common.model.dto.response

import black_and_white.drink.common.model.enumeration.DrinkTemperature
import black_and_white.drink.common.model.enumeration.DrinkType

data class DrinkInfoDto(
    val id: Long,
    val name: String,
    val type: DrinkType,
    val temperature: DrinkTemperature,
    val coffeeShop: String,
    val score: Float,
    val composition: String?,
    val isFavorite: Boolean,
)