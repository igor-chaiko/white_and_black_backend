package black_and_white.coffee_shop.common.repository

import black_and_white.coffee_shop.common.model.entity.CoffeeShop
import org.springframework.data.repository.CrudRepository

interface CoffeeShopRepository: CrudRepository<CoffeeShop, Long> {
    fun findAllByIdIn(ids: Collection<Long>): List<CoffeeShop>
}
