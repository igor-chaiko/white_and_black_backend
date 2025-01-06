package black_and_white.coffee_shop.common.service

import black_and_white.coffee_shop.common.converter.convertToInfoDto
import black_and_white.coffee_shop.common.converter.convertToShortDto
import black_and_white.coffee_shop.common.model.dto.response.CoffeeShopInfoDto
import black_and_white.coffee_shop.common.model.dto.response.CoffeeShopShortDto
import black_and_white.drink.common.model.dto.response.DrinkShortDto
import black_and_white.coffee_shop.common.model.entity.CoffeeShop
import black_and_white.coffee_shop.common.repository.CoffeeShopRepository
import black_and_white.drink.common.converter.convertToShortDto
import black_and_white.drink.common.repository.DrinkRepository
import black_and_white.product.common.converter.convertToProductOfCoffeeShopDto
import black_and_white.product.common.model.dto.response.ProductOfCoffeeShopDto
import black_and_white.product.common.repository.ProductRepository
import org.springframework.stereotype.Service

@Service
class CoffeeShopServiceImpl(
    private val coffeeShopRepository: CoffeeShopRepository,
    private val drinkRepository: DrinkRepository,
    private val productRepository: ProductRepository,
) : CoffeeShopService {
    override fun getAllShort() : List<CoffeeShopShortDto> =
        coffeeShopRepository.findAll().map { it.convertToShortDto() }

    override fun getById(id: Long): CoffeeShop =
        coffeeShopRepository.findById(id).orElseThrow{ NoSuchElementException() }

    override fun getDrinksById(coffeeShopId: Long) : List<DrinkShortDto> {
        val fullDrinks = drinkRepository.getByCoffeeShopId(coffeeShopId).ifEmpty {
            throw NoSuchElementException()
        }

        return fullDrinks.map { it.convertToShortDto() }
    }

    override fun getInfoById(coffeeShopId: Long) : CoffeeShopInfoDto =
        getById(coffeeShopId).convertToInfoDto()

    override fun getProductsById(coffeeShopId: Long): List<ProductOfCoffeeShopDto> {
        val coffeeShop = getById(coffeeShopId)
        val productIds = coffeeShop.products.map { it.productId }
        return productRepository.findAllById(productIds).map {
            it.convertToProductOfCoffeeShopDto()
        }
    }
}
