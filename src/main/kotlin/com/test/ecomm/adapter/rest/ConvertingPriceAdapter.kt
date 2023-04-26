package com.test.ecomm.adapter.rest

import com.test.ecomm.adapter.rest.config.ConvertingPriceConfig
import com.test.ecomm.adapter.rest.model.ConvertedPriceDto
import com.test.ecomm.application.execption.ExternalCallException
import com.test.ecomm.application.port.out.ConvertingPricePort
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder


private const val FROM_QUERY_KEY = "from"
private const val TO_QUERY_KEY = "to"
private const val AMOUNT_PARAM = "amount"

private const val API_KEY_HEADER_KEY = "apikey"

@Component
class ConvertingPriceAdapter(
    val restTemplate: RestTemplate,
    val convertingPriceConfig: ConvertingPriceConfig
) : ConvertingPricePort {

    override fun convert(from: String?, to: String?, amount: Double?): Double? {
        try {
            val resourceUrl = buildResourceUrl(from, to, amount)

            val responseEntity: ResponseEntity<ConvertedPriceDto> = restTemplate.exchange(
                resourceUrl,
                HttpMethod.GET,
                HttpEntity(resourceUrl, buildHeaders()),
                ConvertedPriceDto::class.java
            )
            return responseEntity.body?.result
        } catch (e: Exception) {
            throw ExternalCallException("An error happened while converting product price", e)
        }
    }

    private fun buildHeaders(): HttpHeaders {
        val httpHeaders = HttpHeaders()
        httpHeaders.add(API_KEY_HEADER_KEY, convertingPriceConfig.apiKey)
        return httpHeaders
    }

    private fun buildResourceUrl(
        from: String?,
        to: String?,
        amount: Double?
    ): String {
        return UriComponentsBuilder
            .fromHttpUrl(convertingPriceConfig.baseUrl)
            .queryParam(FROM_QUERY_KEY, from)
            .queryParam(TO_QUERY_KEY, to)
            .queryParam(AMOUNT_PARAM, amount)
            .build()
            .toUri().toString()
    }
}
