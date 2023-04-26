package com.test.ecomm.domain

data class Category(var id: Long? = null, var name: String = "", var parent: Category? = null)
