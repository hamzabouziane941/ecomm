package com.test.ecomm.application

import com.test.ecomm.application.execption.DataIntegrityException
import com.test.ecomm.application.execption.EntityNotFoundException
import com.test.ecomm.application.port.`in`.ProcessingProductUseCase
import com.test.ecomm.application.port.out.ConvertingPricePort
import com.test.ecomm.application.port.out.ReadingCategoryPort
import com.test.ecomm.application.port.out.ReadingProductPort
import com.test.ecomm.application.port.out.WritingProductPort
import com.test.ecomm.domain.Product
import org.apache.commons.lang3.StringUtils.isNotBlank
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.stereotype.Component
import java.util.Objects.isNull

@Component
class ProductProcessor(
    val readingCategoryPort: ReadingCategoryPort,
    val readingProductPort: ReadingProductPort,
    val writingProductPort: WritingProductPort,
    val convertingPricePort: ConvertingPricePort
) : ProcessingProductUseCase {

    override fun getProductsByCurrency(currency: String?): List<Product> {
        return readingProductPort.findAll().map {
            if (isNotBlank(currency) && !currency.equals(it.currency)) {
                it.price = convertingPricePort.convert(it.currency, currency, it.price)
                it.currency = currency
            }
            it
        }
    }

    override fun getProduct(id: Long): Product? {
        val product = readingProductPort.findById(id)
        if (isNull(product)) {
            throw EntityNotFoundException("Product with id : '%s' doesn't exist".format(id))
        }
        return product
    }

    @Throws(EntityNotFoundException::class)
    override fun addProduct(product: Product): Product {
        val category = product.category?.id?.let { readingCategoryPort.findById(it) }
        if (isNull(category)) {
            throw EntityNotFoundException("Product's category is not found")
        }
        product.category = category
        return writingProductPort.save(product)
    }

    override fun updateProduct(id: Long, product: Product): Product {
        if (isNull(readingProductPort.findById(id))) {
            throw EntityNotFoundException("Product with id : '%s' doesn't exist".format(id))
        }
        product.id = id
        return writingProductPort.save(product)
    }

    override fun deleteProduct(id: Long) {
        try {
            return writingProductPort.deleteById(id)
        } catch (ex: DataIntegrityViolationException) {
            throw DataIntegrityException(
                "Cannot delete product with id '%s' because it would affect products integrity".format(
                    id
                ), ex
            )
        } catch (ex: EmptyResultDataAccessException) {
            throw EntityNotFoundException("Product with id : '%s' doesn't exist".format(id))
        }
    }
}
