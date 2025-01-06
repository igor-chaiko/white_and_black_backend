package black_and_white.coffee_shop.common.model.dto.response

data class CoffeeShopShortDto(
    val id: Long,
    val name: String,
    val address: String,
    val score: Float,
    val withDog: Boolean?,
    val withLaptop: Boolean?,
    val seats: Boolean?,
    val isFavorite: Boolean,
)
