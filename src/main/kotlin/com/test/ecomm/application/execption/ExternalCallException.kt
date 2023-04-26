package com.test.ecomm.application.execption

class ExternalCallException(message: String, throwable: Throwable) :
    RuntimeException(message, throwable)
