package com.test.ecomm.adapter.web.mapping

import com.test.ecomm.adapter.web.dto.CategoryDto
import com.test.ecomm.adapter.web.dto.ProductDto
import com.test.ecomm.domain.Category
import com.test.ecomm.domain.Product
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface ProductDtoMapper {

    fun productsToProductDtoList(products: List<Product>): List<ProductDto>

    fun productToProductDto(product: Product): ProductDto

    fun productDtoToProduct(productDto: ProductDto): Product

    fun categoryToCategoryDto(category: Category): CategoryDto

    fun categoryDtoToCategory(categoryDto: CategoryDto): Category
}
