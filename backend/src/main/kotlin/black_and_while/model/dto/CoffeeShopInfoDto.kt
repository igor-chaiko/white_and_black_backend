package black_and_while.model.dto

data class CoffeeShopInfoDto(
    val name: String,
    val description: String?,
    val address: String,
    val score: Float,
    val withLaptop: Boolean?,
    val withDog: Boolean?,
    val seats: Boolean?,
)