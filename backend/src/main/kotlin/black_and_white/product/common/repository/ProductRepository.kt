package black_and_white.product.common.repository

import black_and_white.product.common.model.entity.Product
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository : CrudRepository<Product, Long> {
    fun findAllByRoasterId(roaster: Long): Iterable<Product>
}