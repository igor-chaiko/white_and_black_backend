package black_and_white.drink.common.service

import black_and_white.drink.common.converter.convertToInfoDto
import black_and_white.drink.common.converter.convertToShortDto
import black_and_white.drink.common.model.dto.response.DrinkInfoDto
import black_and_white.drink.common.model.dto.response.DrinkShortDto
import black_and_white.drink.review.repository.DrinkReviewRepository
import black_and_white.drink.common.repository.DrinkRepository
import org.springframework.stereotype.Service

@Service
class DrinkServiceImpl(
    private val drinkRepository: DrinkRepository,
) : DrinkService {
    override fun getById(id: Long) =
        drinkRepository.findById(id)

    override fun getShortInfo(id: Long) : DrinkInfoDto {
        val fullDrink = drinkRepository.findById(id).orElseThrow {
            NoSuchElementException("Drink not found")
        }

        return fullDrink.convertToInfoDto()
    }


    override fun getAllDrinksShort() : List<DrinkShortDto> =
        drinkRepository.findAll().map { it.convertToShortDto() }
}
