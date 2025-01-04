package black_and_white.coffee_shop.common.model.dto.response

data class CoffeeShopInfoDto(
    val name: String,
    val description: String?,
    val address: String,
    val score: Float,
    val withLaptop: Boolean?,
    val withDog: Boolean?,
    val seats: Boolean?,
)