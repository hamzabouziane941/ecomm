package com.test.ecomm.adapter.web

import com.test.ecomm.adapter.web.dto.ProductDto
import com.test.ecomm.adapter.web.mapping.ProductDtoMapper
import com.test.ecomm.application.port.`in`.ProcessingProductUseCase
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI


@RestController
@RequestMapping("/products")
class ProductController(
    val productProcessor: ProcessingProductUseCase,
    val productDtoMapper: ProductDtoMapper
) {

    @GetMapping()
    fun getProducts(@RequestParam currency: String?): ResponseEntity<List<ProductDto>> {
        val products = productDtoMapper.productsToProductDtoList(productProcessor.getProductsByCurrency(currency))
        return ResponseEntity.ok().body(products)
    }

    @GetMapping("/{id}")
    fun getProduct(@PathVariable id: Long): ResponseEntity<ProductDto> {
        val productDto =
            productProcessor.getProduct(id)?.let { productDtoMapper.productToProductDto(it) }
        return ResponseEntity.ok().body(productDto)
    }

    @PostMapping
    fun addProduct(@RequestBody productDto: ProductDto): ResponseEntity<ProductDto> {
        val addedProduct =
            productProcessor.addProduct(productDtoMapper.productDtoToProduct(productDto))
        return ResponseEntity.created(URI.create("/products"))
            .body(productDtoMapper.productToProductDto(addedProduct))
    }

    @PutMapping("/{id}")
    fun updateProduct(
        @PathVariable id: Long,
        @RequestBody productDto: ProductDto
    ): ResponseEntity<ProductDto> {
        val updatedProduct =
            productProcessor.updateProduct(id, productDtoMapper.productDtoToProduct(productDto))
        return ResponseEntity.ok().body(productDtoMapper.productToProductDto(updatedProduct))
    }

    @DeleteMapping("/{id}")
    fun deleteProduct(@PathVariable id: Long): ResponseEntity<Void> {
        productProcessor.deleteProduct(id)
        return ResponseEntity.noContent().build()
    }
}
