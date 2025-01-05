package black_and_white.product.common.model.dto.response

import black_and_white.product.common.model.enumeration.ProductSubtype
import black_and_white.product.common.model.enumeration.ProductType

data class ProductInfoDto(
    val id: Long,
    val name: String,
    val roasterId: Long,
    val type: ProductType,
    val subtype: ProductSubtype,
    val score: Float,
    val composition: String?,
    val roaster: String,
)
