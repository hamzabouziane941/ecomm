package com.test.ecomm.adapter.persistence

import com.test.ecomm.adapter.persistence.model.CategoryEntity
import org.springframework.data.repository.CrudRepository

interface CategoryRepository : CrudRepository<CategoryEntity, Long> {

    override fun findAll(): List<CategoryEntity>

    fun existsByParent(parent: CategoryEntity): Boolean
}
