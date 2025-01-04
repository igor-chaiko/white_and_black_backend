package black_and_white.drink.common.converter

import black_and_white.drink.common.model.dto.response.DrinkInfoDto
import black_and_white.drink.common.model.dto.response.DrinkShortDto
import black_and_white.drink.common.model.entity.Drink
import kotlin.math.round

fun Drink.convertToShortDto() = DrinkShortDto(
    id = this.id!!,
    name = this.name,
    type = this.type,
    temperature = this.temperature,
    score = if (this.scoreCount == 0) 0f else round((this.scoreSum.toFloat() / this.scoreCount) * 10) / 10f,
)

fun Drink.convertToInfoDto() = DrinkInfoDto(
    id = this.id!!,
    name = this.name,
    type = this.type,
    temperature = this.temperature,
    coffeeShop = this.coffeeShop,
    score = if (this.scoreCount == 0) 0f else round((this.scoreSum.toFloat() / this.scoreCount) * 10) / 10f,
    composition = this.composition
)