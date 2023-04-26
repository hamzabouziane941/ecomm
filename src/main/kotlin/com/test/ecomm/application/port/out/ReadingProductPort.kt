package com.test.ecomm.application.port.out

import com.test.ecomm.domain.Product

interface ReadingProductPort {

    fun findAll(): List<Product>

    fun findById(id: Long): Product?
}
