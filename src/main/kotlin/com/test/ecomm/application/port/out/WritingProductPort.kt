package com.test.ecomm.application.port.out

import com.test.ecomm.application.execption.ConstraintsViolationException
import com.test.ecomm.domain.Product

interface WritingProductPort {

    @Throws(ConstraintsViolationException::class)
    fun save(product: Product): Product

    fun deleteById(id: Long)
}
