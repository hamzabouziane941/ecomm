package com.test.ecomm.adapter.web.mapping

import com.test.ecomm.adapter.web.dto.CategoryDto
import com.test.ecomm.domain.Category
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface CategoryDtoMapper {

    fun categoriesToCategoryDtoList(categories: List<Category>): List<CategoryDto>

    fun categoryToCategoryDto(category: Category): CategoryDto

    fun categoryDtoToCategory(categoryDto: CategoryDto): Category
}
