package black_and_while.repository

import black_and_while.model.entity.CoffeeShop
import org.springframework.data.repository.CrudRepository

interface CoffeeShopRepository: CrudRepository<CoffeeShop, Long> {
}