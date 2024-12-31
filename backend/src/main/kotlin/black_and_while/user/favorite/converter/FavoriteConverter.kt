package black_and_while.user.favorite.converter

import black_and_while.model.entity.enumeration.EntityToLike
import black_and_while.model.entity.CoffeeShop
import black_and_while.model.entity.Drink
import black_and_while.user.favorite.model.dto.response.CoffeeShopFavoriteDto
import black_and_while.user.favorite.model.dto.response.DrinkFavoriteDto

fun String.convertToFavoriteEntity(): EntityToLike = when (this) {
    "COFFEE_SHOP" -> EntityToLike.COFFEE_SHOP
    "DRINK" -> EntityToLike.DRINK
    else -> throw IllegalArgumentException("Unsupported entity type")
}

fun CoffeeShop.convertToDto() = CoffeeShopFavoriteDto(
    id = this.id!!,
    name = this.name,
    address = this.address,
    score = if (this.scoreCount == 0) 0f else (this.scoreSum.toFloat() / this.scoreCount),
    withDog = this.withDog,
    withLaptop = this.withLaptop,
    seats = this.seats,
)

fun Drink.convertToDto() = DrinkFavoriteDto(
    id = this.id!!,
    name = this.name,
    type = this.type,
    temperature = this.temperature,
    score = if (this.scoreCount == 0) 0f else (this.scoreSum.toFloat() / this.scoreCount),
)
