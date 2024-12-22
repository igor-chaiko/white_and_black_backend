package black_and_while.model.dto

import black_and_while.model.entity.enumeration.DrinkTemperature
import black_and_while.model.entity.enumeration.DrinkType

data class DrinkInfoDto(
    val id: Long,
    val name: String,
    val type: DrinkType,
    val temperature: DrinkTemperature,
    val coffeeShop: String,
    val score: Float,
    val composition: String?,
)