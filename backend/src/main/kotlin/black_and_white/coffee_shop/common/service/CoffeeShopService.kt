package black_and_white.coffee_shop.common.service

import black_and_white.coffee_shop.common.model.dto.response.CoffeeShopInfoDto
import black_and_white.coffee_shop.common.model.dto.response.CoffeeShopShortDto
import black_and_white.coffee_shop.common.model.entity.CoffeeShop
import black_and_white.drink.common.model.dto.response.DrinkShortDto

interface CoffeeShopService {
    fun getAllShort() : List<CoffeeShopShortDto>

    fun getById(id: Long): CoffeeShop

    fun getDrinksById(coffeeShopId: Long) : List<DrinkShortDto>

    fun getInfoById(coffeeShopId: Long) : CoffeeShopInfoDto
}