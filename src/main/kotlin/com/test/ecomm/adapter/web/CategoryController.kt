package com.test.ecomm.adapter.web

import com.test.ecomm.adapter.web.dto.CategoryDto
import com.test.ecomm.adapter.web.mapping.CategoryDtoMapper
import com.test.ecomm.application.port.`in`.ProcessingCategoryUseCase
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

@RestController
@RequestMapping("categories")
@CrossOrigin(origins = ["http://localhost:3000"])
class CategoryController(
    val categoryProcessor: ProcessingCategoryUseCase,
    val categoryMapper: CategoryDtoMapper
) {

    @GetMapping
    fun getCategories(): ResponseEntity<List<CategoryDto>> {
        val categoryDtoList =
            categoryMapper.categoriesToCategoryDtoList(categoryProcessor.getCategories());
        return ResponseEntity.ok().body(categoryDtoList)
    }

    @GetMapping("/{id}")
    fun getCategory(@PathVariable id: Long): ResponseEntity<CategoryDto> {
        val categoryDto =
            categoryProcessor.getCategory(id)?.let { categoryMapper.categoryToCategoryDto(it) }
        return ResponseEntity.ok().body(categoryDto)
    }

    @PostMapping
    fun addCategory(@RequestBody categoryDto: CategoryDto): ResponseEntity<CategoryDto> {
        val addedCategory =
            categoryProcessor.addCategory(categoryMapper.categoryDtoToCategory(categoryDto))
        return ResponseEntity.created(URI.create("/categories"))
            .body(categoryMapper.categoryToCategoryDto(addedCategory))
    }

    @PutMapping("/{id}")
    fun updateCategory(
        @PathVariable id: Long,
        @RequestBody categoryDto: CategoryDto
    ): ResponseEntity<CategoryDto> {
        val updatedCategory =
            categoryProcessor.updateCategory(id, categoryMapper.categoryDtoToCategory(categoryDto))
        return ResponseEntity.ok()
            .body(categoryMapper.categoryToCategoryDto(updatedCategory))
    }

    @DeleteMapping("/{id}")
    fun deleteCategory(@PathVariable id: Long): ResponseEntity<Void> {
        categoryProcessor.deleteCategory(id)
        return ResponseEntity.noContent().build()
    }
}
