package black_and_white.product.common.service

import black_and_white.product.common.model.dto.response.ProductInfoDto
import black_and_white.product.common.model.dto.response.ProductShortDto

interface ProductService {
    fun findAllByRoasterWithId(roasterId: Long): List<ProductShortDto>

    fun findInfoById(id: Long): ProductInfoDto
}