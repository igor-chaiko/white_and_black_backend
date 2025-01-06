package black_and_white.coffee_shop.common.model.entity

import org.springframework.data.relational.core.mapping.Table

@Table("coffee_shops_products")
data class ProductRef(
    val productId: Long,
)