package black_and_white.user.favorite.model.dto.response

data class CoffeeShopFavoriteDto(
    val id: Long,
    val name: String,
    val address: String,
    val score: Float,
    val withDog: Boolean?,
    val withLaptop: Boolean?,
    val seats: Boolean?,
)
