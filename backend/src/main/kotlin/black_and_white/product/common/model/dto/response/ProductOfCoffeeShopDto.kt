package black_and_white.product.common.model.dto.response

import black_and_white.product.common.model.enumeration.ProductSubtype
import black_and_white.product.common.model.enumeration.ProductType

data class ProductOfCoffeeShopDto(
    val id: Long,
    val name: String,
    val composition: String?,
    val type: ProductType,
    val subtype: ProductSubtype,
    val score: Float,
    val roasterId: Long,
    val roaster: String,
)
