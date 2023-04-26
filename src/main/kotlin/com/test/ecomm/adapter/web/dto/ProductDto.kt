package com.test.ecomm.adapter.web.dto

data class ProductDto(
    var id: Long? = null,
    var name: String = "",
    var price: Double? = null,
    var currency: String = "",
    var category: CategoryDto? = null
)
