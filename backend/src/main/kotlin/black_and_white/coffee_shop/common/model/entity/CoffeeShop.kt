package black_and_white.coffee_shop.common.model.entity

import black_and_white.drink.common.model.entity.Drink
import black_and_white.coffee_shop.review.model.entity.CoffeeShopReview
import black_and_white.product.common.model.entity.Product
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.MappedCollection
import org.springframework.data.relational.core.mapping.Table

@Table(name = "coffee_shops")
data class CoffeeShop(
    @Id var id: Long? = null,
    val name: String,
    val description: String?,
    val address: String,
    var scoreSum: Int,
    var scoreCount: Int,
    @Column("withlaptop")
    val withLaptop: Boolean? = null,
    @Column("withdog")
    val withDog: Boolean? = null,
    val seats: Boolean? = null,

    @MappedCollection(idColumn = "coffee_shop_id")
    var drinks: Set<Drink> = mutableSetOf(),

    @MappedCollection(idColumn = "coffee_shop_id")
    var reviews: Set<CoffeeShopReview> = mutableSetOf(),

    @MappedCollection(idColumn = "coffee_shop_id", keyColumn = "product_id")
    var products: Set<ProductRef> = mutableSetOf(),
)