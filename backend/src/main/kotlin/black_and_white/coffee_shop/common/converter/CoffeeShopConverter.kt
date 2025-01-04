package black_and_white.coffee_shop.common.converter

import black_and_white.coffee_shop.common.model.dto.response.CoffeeShopInfoDto
import black_and_white.coffee_shop.common.model.dto.response.CoffeeShopShortDto
import black_and_white.coffee_shop.common.model.entity.CoffeeShop
import kotlin.math.round

fun CoffeeShop.convertToInfoDto() = CoffeeShopInfoDto(
    name = this.name,
    description = this.description,
    address = this.address,
    score = if (this.scoreCount == 0) 0f else round((this.scoreSum.toFloat() / this.scoreCount) * 10) / 10f,
    withLaptop = this.withLaptop,
    withDog = this.withDog,
    seats = this.seats,
)

fun CoffeeShop.convertToShortDto() = CoffeeShopShortDto(
        id = this.id!!,
        name = this.name,
        address = this.address,
        score  = if (this.scoreCount == 0) 0f else round((this.scoreSum.toFloat() / this.scoreCount) * 10) / 10f,
        withDog = this.withDog,
        withLaptop = this.withLaptop,
        seats = this.seats,
)