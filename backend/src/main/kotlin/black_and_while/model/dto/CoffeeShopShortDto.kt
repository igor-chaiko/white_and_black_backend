package black_and_while.model.dto

data class CoffeeShopShortDto(
    val id: Long,
    val name: String,
    val address: String,
    val score: Float,
    val withDog: Boolean?,
    val withLaptop: Boolean?,
    val seats: Boolean?,
)