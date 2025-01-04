package black_and_white.drink.common.converter

import black_and_white.drink.common.model.dto.response.DrinkInfoDto
import black_and_white.drink.common.model.dto.response.DrinkShortDto
import black_and_white.drink.common.model.entity.Drink

fun Drink.convertToShortDto() = DrinkShortDto(
    id = this.id!!,
    name = this.name,
    type = this.type,
    temperature = this.temperature,
    score = if (this.scoreCount == 0) 0f else (this.scoreSum.toFloat() / this.scoreCount),
)

fun Drink.convertToInfoDto() = DrinkInfoDto(
    id = this.id!!,
    name = this.name,
    type = this.type,
    temperature = this.temperature,
    coffeeShop = this.coffeeShop,
    score = if (this.scoreCount == 0) 0f else (this.scoreSum.toFloat() / this.scoreCount),
    composition = this.composition
)