package black_and_white.drink.common.model.entity

import black_and_white.drink.review.model.entity.DrinkReview
import black_and_white.drink.common.model.enumeration.DrinkTemperature
import black_and_white.drink.common.model.enumeration.DrinkType
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.MappedCollection
import org.springframework.data.relational.core.mapping.Table

@Table(name = "drinks")
data class Drink(
    @Id var id: Long? = null,
    val coffeeShopId: Long,
    val name: String,
    val type: DrinkType,
    //desription?
    var scoreSum: Int,
    var scoreCount: Int,
    val composition: String?,
    val coffeeShop: String,
    val temperature: DrinkTemperature,

    @MappedCollection(idColumn = "drink_id")
    var reviews: Set<DrinkReview> = mutableSetOf()
)
