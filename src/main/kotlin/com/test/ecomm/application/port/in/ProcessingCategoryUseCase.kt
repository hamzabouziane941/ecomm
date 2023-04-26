package com.test.ecomm.application.port.`in`

import com.test.ecomm.domain.Category

interface ProcessingCategoryUseCase {
    fun getCategories(): List<Category>

    fun getCategory(id: Long): Category?

    fun addCategory(category: Category): Category

    fun updateCategory(id: Long, category: Category): Category

    fun deleteCategory(id: Long)
}
