package black_and_white.drink.common.service

import black_and_white.drink.common.model.dto.response.DrinkInfoDto
import black_and_white.drink.common.model.dto.response.DrinkShortDto
import black_and_white.drink.common.model.entity.Drink
import org.springframework.stereotype.Service
import java.util.*

interface DrinkService {
    fun getById(id: Long): Optional<Drink>

    fun getShortInfo(id: Long): DrinkInfoDto

    fun getAllDrinksShort() : List<DrinkShortDto>
}