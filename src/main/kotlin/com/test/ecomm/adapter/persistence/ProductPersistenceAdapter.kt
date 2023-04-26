package com.test.ecomm.adapter.persistence

import com.test.ecomm.adapter.persistence.mapping.ProductEntityMapper
import com.test.ecomm.application.execption.ConstraintsViolationException
import com.test.ecomm.application.port.out.ReadingProductPort
import com.test.ecomm.application.port.out.WritingProductPort
import com.test.ecomm.domain.Product
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.stereotype.Component

@Component
class ProductPersistenceAdapter(
    val productRepository: ProductRepository,
    val productEntityMapper: ProductEntityMapper
) : ReadingProductPort, WritingProductPort {

    override fun findAll(): List<Product> {
        val productEntities = productRepository.findAll()
        return productEntityMapper.productEntitiesToProducts(productEntities)
    }

    override fun findById(id: Long): Product? {
        val productOptional = productRepository.findById(id)
        return productOptional.orElse(null)?.let {
            productEntityMapper.productEntityToProduct(it)
        }
    }

    override fun save(product: Product): Product {
        try {
            val productEntity =
                productRepository.save(productEntityMapper.productToProductEntity(product));
            return productEntityMapper.productEntityToProduct(productEntity)
        } catch (e: DataIntegrityViolationException) {
            throw e.message?.let { ConstraintsViolationException(it) }!!
        }
    }

    override fun deleteById(id: Long) {
        productRepository.deleteById(id)
    }
}
