package black_and_white.roaster.common.model.entity

import black_and_white.coffee_shop.review.model.entity.CoffeeShopReview
import black_and_white.drink.common.model.entity.Drink
import black_and_white.product.common.model.entity.Product
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.MappedCollection
import org.springframework.data.relational.core.mapping.Table

@Table(name = "roasters")
data class Roaster(
    @Id var id: Long? = null,
    val name: String,
    val description: String?,
    var scoreSum: Int,
    var scoreCount: Int,

    @MappedCollection(idColumn = "roaster_id")
    var products: Set<Product> = mutableSetOf(),

//    @MappedCollection(idColumn = "roaster_id")
//    var reviews: Set<RoasterReview> = mutableSetOf(),
)
