package com.test.ecomm.adapter.persistence

import com.test.ecomm.adapter.persistence.model.ProductEntity
import org.springframework.data.repository.CrudRepository

interface ProductRepository : CrudRepository<ProductEntity, Long> {
    override fun findAll(): List<ProductEntity>
}
