package com.test.ecomm.application.port.out

import com.test.ecomm.application.execption.ConstraintsViolationException
import com.test.ecomm.domain.Category

interface WritingCategoryPort {

    @Throws(ConstraintsViolationException::class)
    fun save(category: Category): Category

    fun deleteById(id: Long)
}
