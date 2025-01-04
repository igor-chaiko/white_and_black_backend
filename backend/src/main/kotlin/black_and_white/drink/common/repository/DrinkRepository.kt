package black_and_white.drink.common.repository

import black_and_white.drink.common.model.entity.Drink
import org.springframework.data.repository.CrudRepository

interface DrinkRepository: CrudRepository<Drink, Long> {
    fun getByCoffeeShopId(coffeeShopId: Long): List<Drink>

    fun findAllByIdIn(ids: Collection<Long>): List<Drink>
}
