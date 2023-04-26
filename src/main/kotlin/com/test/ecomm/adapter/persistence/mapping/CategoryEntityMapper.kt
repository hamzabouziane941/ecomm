package com.test.ecomm.adapter.persistence.mapping

import com.test.ecomm.adapter.persistence.model.CategoryEntity
import com.test.ecomm.domain.Category
import org.mapstruct.Mapper
import org.mapstruct.NullValuePropertyMappingStrategy
import org.mapstruct.ReportingPolicy

@Mapper(
    componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
interface CategoryEntityMapper {

    fun categoryEntitiesToCategoryList(categoryEntities: List<CategoryEntity>): List<Category>

    fun categoryEntityToCategory(categoryEntity: CategoryEntity?): Category

    fun categoryToCategoryEntity(category: Category?): CategoryEntity
}
