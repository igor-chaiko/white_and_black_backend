package black_and_white.product.common.model.entity

import black_and_white.product.common.model.enumeration.ProductSubtype
import black_and_white.product.common.model.enumeration.ProductType
import black_and_white.product.review.model.entity.ProductReview
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.MappedCollection
import org.springframework.data.relational.core.mapping.Table

@Table(name = "products")
data class Product(
    @Id var id: Long? = null,
    val roasterId: Long,
    val name: String,
    val type: ProductType,
    val subtype: ProductSubtype,
    var scoreSum: Int,
    var scoreCount: Int,
    val composition: String?,
    val roasterName: String,

    @MappedCollection(idColumn = "product_id")
    var reviews: Set<ProductReview> = mutableSetOf()
)
