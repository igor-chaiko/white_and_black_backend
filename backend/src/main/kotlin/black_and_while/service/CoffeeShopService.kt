package black_and_while.service

import black_and_while.model.dto.CoffeeShopInfoDto
import black_and_while.model.dto.CoffeeShopShortDto
import black_and_while.model.dto.DrinkShortDto
import black_and_while.model.entity.CoffeeShop
import black_and_while.repository.CoffeeShopRepository
import black_and_while.repository.DrinksRepository
import org.springframework.stereotype.Service

@Service
class CoffeeShopService(
    private val coffeeShopRepository: CoffeeShopRepository,
    private val drinksRepository: DrinksRepository,
) {
    fun getAllShort() : List<CoffeeShopShortDto> {
        val coffeeShops = coffeeShopRepository.findAll()
        val coffeeShopDtos = mutableListOf<CoffeeShopShortDto>()
        coffeeShops.forEach {
            coffeeShopDtos.addLast(
                CoffeeShopShortDto(
                    it.id!!,
                    it.name,
                    it.address,
                    if (it.scoreCount == 0) 0f else (it.scoreSum.toFloat() / it.scoreCount),
                    it.withDog,
                    it.withLaptop,
                    it.seats
                )
            )
        }
        return coffeeShopDtos
    }

    fun getById(id: Long): CoffeeShop =
        coffeeShopRepository.findById(id).orElseThrow{ NoSuchElementException() }

    fun getDrinksById(coffeeShopId: Long) : List<DrinkShortDto> {
        val fullDrinks = drinksRepository.getByCoffeeShopId(coffeeShopId).ifEmpty {
            throw NoSuchElementException()
        }
        val shortDrinks = mutableListOf<DrinkShortDto>()
        fullDrinks.forEach {
            shortDrinks.addLast(
                DrinkShortDto(
                    it.id!!,
                    it.name,
                    it.type,
                    it.temperature,
                    if (it.scoreCount == 0) 0f else (it.scoreSum.toFloat() / it.scoreCount),
                )
            )
        }
        return shortDrinks
    }

    fun getInfoById(coffeeShopId: Long) : CoffeeShopInfoDto {
        val fullInfo = getById(coffeeShopId)
        return CoffeeShopInfoDto(
            name = fullInfo.name,
            description = fullInfo.description,
            address = fullInfo.address,
            score = if (fullInfo.scoreCount == 0) 0f else (fullInfo.scoreSum.toFloat() / fullInfo.scoreCount),
            withLaptop = fullInfo.withLaptop,
            withDog = fullInfo.withDog,
            seats = fullInfo.seats
        )
    }
}