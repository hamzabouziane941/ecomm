package com.test.ecomm.application.execption

class DataIntegrityException(message: String, throwable: Throwable) :
    RuntimeException(message, throwable)
