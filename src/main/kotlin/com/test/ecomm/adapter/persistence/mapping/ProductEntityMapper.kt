package com.test.ecomm.adapter.persistence.mapping

import com.test.ecomm.adapter.persistence.model.CategoryEntity
import com.test.ecomm.adapter.persistence.model.ProductEntity
import com.test.ecomm.domain.Category
import com.test.ecomm.domain.Product
import org.mapstruct.Mapper
import org.mapstruct.NullValuePropertyMappingStrategy
import org.mapstruct.ReportingPolicy

@Mapper(
    componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
interface ProductEntityMapper {

    fun productEntitiesToProducts(productEntities: List<ProductEntity>): List<Product>

    fun productEntityToProduct(productEntity: ProductEntity?): Product

    fun productToProductEntity(product: Product): ProductEntity

    fun categoryEntityToCategory(categoryEntity: CategoryEntity): Category

    fun categoryToCategoryEntity(category: Category): CategoryEntity
}
