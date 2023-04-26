package com.test.ecomm.application.port.out

import com.test.ecomm.application.execption.ExternalCallException

interface ConvertingPricePort {

    @Throws(ExternalCallException::class)
    fun convert(from: String?, to: String?, amount: Double?): Double?
}
