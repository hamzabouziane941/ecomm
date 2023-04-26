package com.test.ecomm.domain

data class Product(
    var id: Long? = null,
    var name: String = "",
    var price: Double? = null,
    var currency: String? = "",
    var category: Category? = null
)
