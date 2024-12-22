package black_and_while.model.entity

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
)