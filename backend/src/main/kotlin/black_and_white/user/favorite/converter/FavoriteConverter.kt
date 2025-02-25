package black_and_white.user.favorite.converter

import black_and_white.user.favorite.model.enumeration.EntityToLike
import black_and_white.coffee_shop.common.model.entity.CoffeeShop
import black_and_white.drink.common.model.entity.Drink
import black_and_white.user.favorite.model.dto.response.CoffeeShopFavoriteDto
import black_and_white.user.favorite.model.dto.response.DrinkFavoriteDto
import kotlin.math.round

fun String.convertToFavoriteEntity(): EntityToLike = when (this) {
    "COFFEE_SHOP" -> EntityToLike.COFFEE_SHOP
    "DRINK" -> EntityToLike.DRINK
    else -> throw IllegalArgumentException("Unsupported entity type")
}

fun CoffeeShop.convertToDto() = CoffeeShopFavoriteDto(
    id = this.id!!,
    name = this.name,
    address = this.address,
    score = if (this.scoreCount == 0) 0f else round((this.scoreSum.toFloat() / this.scoreCount) * 10) / 10f,
    withDog = this.withDog,
    withLaptop = this.withLaptop,
    seats = this.seats,
)

fun Drink.convertToDto() = DrinkFavoriteDto(
    id = this.id!!,
    name = this.name,
    type = this.type,
    temperature = this.temperature,
    score = if (this.scoreCount == 0) 0f else round((this.scoreSum.toFloat() / this.scoreCount) * 10) / 10f,
)
