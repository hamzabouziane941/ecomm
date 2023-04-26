package com.test.ecomm.application.port.`in`

import com.test.ecomm.domain.Product

interface ProcessingProductUseCase {
    fun getProductsByCurrency(currency: String?): List<Product>

    fun getProduct(id: Long): Product?

    fun addProduct(product: Product): Product

    fun updateProduct(id: Long, product: Product): Product

    fun deleteProduct(id: Long)
}
