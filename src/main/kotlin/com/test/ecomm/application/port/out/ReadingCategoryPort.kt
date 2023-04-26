package com.test.ecomm.application.port.out

import com.test.ecomm.domain.Category

interface ReadingCategoryPort {

    fun findAll(): List<Category>

    fun findById(id: Long): Category?

    fun checkIfParentExists(parentCategory: Category?): Boolean
}
