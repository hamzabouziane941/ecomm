package com.test.ecomm.application

import com.test.ecomm.application.execption.ConstraintsViolationException
import com.test.ecomm.application.execption.DataIntegrityException
import com.test.ecomm.application.execption.EntityNotFoundException
import com.test.ecomm.application.port.`in`.ProcessingCategoryUseCase
import com.test.ecomm.application.port.out.ReadingCategoryPort
import com.test.ecomm.application.port.out.WritingCategoryPort
import com.test.ecomm.domain.Category
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.stereotype.Component
import java.util.Objects.isNull

@Component
class CategoryProcessor(
    val readingCategoryPort: ReadingCategoryPort,
    val writingCategoryPort: WritingCategoryPort
) : ProcessingCategoryUseCase {
    override fun getCategories(): List<Category> {
        return readingCategoryPort.findAll()
    }

    @Throws(EntityNotFoundException::class)
    override fun getCategory(id: Long): Category? {
        val category = readingCategoryPort.findById(id)
        if (isNull(category)) {
            throw EntityNotFoundException("Category with id : '%s' doesn't exist".format(id))
        }
        return category
    }

    @Throws(ConstraintsViolationException::class)
    override fun addCategory(category: Category): Category {
        category.parent?.let { categoryIt ->
            category.parent = findParentCategory(categoryIt)
        }
        return writingCategoryPort.save(category)
    }

    override fun updateCategory(id: Long, category: Category): Category {
        if (isNull(readingCategoryPort.findById(id))) {
            throw EntityNotFoundException("Category with id : '%s' doesn't exist".format(id))
        }
        category.id = id
        return writingCategoryPort.save(category)
    }

    override fun deleteCategory(id: Long) {
        try {
            return writingCategoryPort.deleteById(id)
        } catch (ex: DataIntegrityViolationException) {
            throw DataIntegrityException(
                "Cannot delete category with id '%s' because it has child categories".format(
                    id
                ), ex
            )
        } catch (ex: EmptyResultDataAccessException) {
            throw EntityNotFoundException("Category with id : '%s' doesn't exist".format(id))
        }
    }

    private fun findParentCategory(category: Category): Category? {
        return category.id?.let { id ->
            readingCategoryPort.findById(id)
        }
    }
}
