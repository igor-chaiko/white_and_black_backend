package black_and_white.roaster.common.converter

import black_and_white.roaster.common.model.dto.response.RoasterInfoDto
import black_and_white.roaster.common.model.dto.response.RoasterShortDto
import black_and_white.roaster.common.model.entity.Roaster
import kotlin.math.round

fun Roaster.convertToShortDto() = RoasterShortDto(
    id = this.id!!,
    name = this.name,
    score = if (this.scoreCount == 0) 0f else round((this.scoreSum.toFloat() / this.scoreCount) * 10) / 10f,
)

fun Roaster.convertToInfoDto() = RoasterInfoDto(
    name = this.name,
    score = if (this.scoreCount == 0) 0f else round((this.scoreSum.toFloat() / this.scoreCount) * 10) / 10f,
    description = this.description,
)