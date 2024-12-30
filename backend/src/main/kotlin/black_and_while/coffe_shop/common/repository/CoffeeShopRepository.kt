package black_and_while.coffe_shop.common.repository

import black_and_while.model.entity.CoffeeShop
import org.springframework.data.repository.CrudRepository

interface CoffeeShopRepository: CrudRepository<CoffeeShop, Long> {
}
