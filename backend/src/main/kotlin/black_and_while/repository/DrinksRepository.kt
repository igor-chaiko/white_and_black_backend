package black_and_while.repository

import black_and_while.model.entity.Drink
import org.springframework.data.repository.CrudRepository

interface DrinksRepository: CrudRepository<Drink, Long> {
    fun getByCoffeeShopId(coffeeShopId: Long): List<Drink>
}