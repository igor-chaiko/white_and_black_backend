package black_and_while.user.favorite

import black_and_while.model.entity.enumeration.EntityToLike

fun String.convertToFavoriteEntity(): EntityToLike = when (this) {
    "COFFEE_SHOP" -> EntityToLike.COFFEE_SHOP
    "DRINK" -> EntityToLike.DRINK
    else -> throw IllegalArgumentException("Unsupported entity type")
}
