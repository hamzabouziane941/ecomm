package com.test.ecomm.adapter.web.errorhandling

import com.test.ecomm.application.execption.ConstraintsViolationException
import com.test.ecomm.application.execption.DataIntegrityException
import com.test.ecomm.application.execption.EntityNotFoundException
import com.test.ecomm.application.execption.ExternalCallException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ExceptionControllerAdvice {

    @ExceptionHandler
    fun handleUnknownEntityException(ex: EntityNotFoundException): ResponseEntity<ErrorMessage> {
        val errorMessage = ErrorMessage(
            HttpStatus.NOT_FOUND.value(),
            ex.message
        )
        return ResponseEntity(errorMessage, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler
    fun handleConstraintsViolationException(ex: ConstraintsViolationException): ResponseEntity<ErrorMessage> {
        val errorMessage = ErrorMessage(
            HttpStatus.CONFLICT.value(),
            ex.message
        )
        return ResponseEntity(errorMessage, HttpStatus.CONFLICT)
    }

    @ExceptionHandler
    fun handleDataIntegrityException(ex: DataIntegrityException): ResponseEntity<ErrorMessage> {
        val errorMessage = ErrorMessage(
            HttpStatus.CONFLICT.value(),
            ex.message
        )
        return ResponseEntity(errorMessage, HttpStatus.CONFLICT)
    }

    @ExceptionHandler
    fun handleExternalCallException(ex: ExternalCallException): ResponseEntity<ErrorMessage> {
        val errorMessage = ErrorMessage(
            HttpStatus.SERVICE_UNAVAILABLE.value(),
            ex.message
        )
        return ResponseEntity(errorMessage, HttpStatus.SERVICE_UNAVAILABLE)
    }

    @ExceptionHandler
    fun handleException(ex: Exception): ResponseEntity<ErrorMessage> {
        val errorMessage = ErrorMessage(
            HttpStatus.INTERNAL_SERVER_ERROR.value(),
            ex.message
        )
        return ResponseEntity(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR)
    }


}
