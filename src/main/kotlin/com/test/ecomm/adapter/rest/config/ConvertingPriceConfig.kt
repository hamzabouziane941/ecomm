package com.test.ecomm.adapter.rest.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
data class ConvertingPriceConfig(
    @Value("\${currency.converter.baseUrl}")
    val baseUrl: String = "",
    @Value("\${currency.converter.apiKey}")
    val apiKey: String = ""
)
