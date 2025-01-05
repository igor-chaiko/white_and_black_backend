package black_and_white.product.common.service

import black_and_white.product.common.converter.convertToInfoDto
import black_and_white.product.common.converter.convertToShortDto
import black_and_white.product.common.model.dto.response.ProductInfoDto
import black_and_white.product.common.model.dto.response.ProductShortDto
import black_and_white.product.common.repository.ProductRepository
import org.springframework.stereotype.Service

@Service
class ProductServiceImpl(
    private val productRepository: ProductRepository,
) : ProductService {
    override fun findAllByRoasterWithId(roasterId: Long): List<ProductShortDto> {
        return productRepository.findAllByRoasterId(roasterId).map{ it.convertToShortDto() }
    }

    override fun findInfoById(id: Long): ProductInfoDto =
        productRepository.findById(id)
            .orElseThrow{ NoSuchElementException("Продукт с id ${id} не найден") }
            .convertToInfoDto()

}