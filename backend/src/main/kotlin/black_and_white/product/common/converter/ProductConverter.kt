package black_and_white.product.common.converter

import black_and_white.product.common.model.dto.response.ProductDisplayDto
import black_and_white.product.common.model.dto.response.ProductInfoDto
import black_and_white.product.common.model.dto.response.ProductShortDto
import black_and_white.product.common.model.entity.Product
import kotlin.math.round

fun Product.convertToShortDto() = ProductShortDto(
    id = this.id!!,
    name = this.name,
    composition = this.composition,
    type = this.type,
    subtype = this.subtype,
    score = if (this.scoreCount == 0) 0f else round((this.scoreSum.toFloat() / this.scoreCount) * 10) / 10f,
    roasterId = this.roasterId,
    roaster = this.roasterName,
)

fun Product.convertToDisplayDto() = ProductDisplayDto(
    id = this.id!!,
    name = this.name,
    composition = this.composition,
    type = this.type,
    subtype = this.subtype,
    score = if (this.scoreCount == 0) 0f else round((this.scoreSum.toFloat() / this.scoreCount) * 10) / 10f,
    roasterId = this.roasterId,
    roaster = this.roasterName,
)

fun Product.convertToInfoDto() = ProductInfoDto(
    id = this.id!!,
    name = this.name,
    composition = this.composition,
    type = this.type,
    subtype = this.subtype,
    score = if (this.scoreCount == 0) 0f else round((this.scoreSum.toFloat() / this.scoreCount) * 10) / 10f,
    roasterId = this.roasterId,
    roaster = this.roasterName,
)
