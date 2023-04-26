package com.test.ecomm.adapter.persistence

import com.test.ecomm.adapter.persistence.mapping.CategoryEntityMapper
import com.test.ecomm.application.execption.ConstraintsViolationException
import com.test.ecomm.application.port.out.ReadingCategoryPort
import com.test.ecomm.application.port.out.WritingCategoryPort
import com.test.ecomm.domain.Category
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.stereotype.Component
import java.util.Objects.isNull

@Component
class CategoryPersistenceAdapter(
    val categoryRepository: CategoryRepository,
    val categoryEntityMapper: CategoryEntityMapper
) : ReadingCategoryPort, WritingCategoryPort {

    override fun findAll(): List<Category> {
        val categoryEntities = categoryRepository.findAll()
        return categoryEntityMapper.categoryEntitiesToCategoryList(categoryEntities)
    }

    override fun findById(id: Long): Category? {
        val categoryEntityOptional = categoryRepository.findById(id);
        return categoryEntityOptional.orElse(null)?.let {
            categoryEntityMapper.categoryEntityToCategory(it)
        }
    }

    override fun checkIfParentExists(parentCategory: Category?): Boolean {
        if (isNull(parentCategory)) {
            return false
        }
        return categoryRepository.existsByParent(
            categoryEntityMapper.categoryToCategoryEntity(parentCategory)
        )
    }

    override fun save(category: Category): Category {
        try {
            val categoryEntity =
                categoryRepository.save(categoryEntityMapper.categoryToCategoryEntity(category))
            return categoryEntityMapper.categoryEntityToCategory(categoryEntity)
        } catch (e: DataIntegrityViolationException) {
            throw e.message?.let { ConstraintsViolationException(it) }!!
        }
    }

    override fun deleteById(id: Long) {
        categoryRepository.deleteById(id)
    }
}
