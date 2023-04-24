package com.jordanbegian.apitemplate.exception

import org.springframework.boot.web.error.ErrorAttributeOptions
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.HttpServerErrorException
import org.springframework.web.reactive.function.server.ServerRequest


@Component
class GlobalErrorAttributes : DefaultErrorAttributes() {
    override fun getErrorAttributes(request: ServerRequest, options: ErrorAttributeOptions?): Map<String, Any?> {
        return when(val error: Throwable = getError(request)) {
            is HttpClientErrorException -> mapOf(
                "message" to error.statusText,
                "statusCode" to error.rawStatusCode
                )
            is HttpServerErrorException -> mapOf(
                "message" to error.statusText,
                "statusCode" to error.rawStatusCode
            )
            else -> mapOf(
                "message" to error.message,
                "statusCode" to HttpStatus.INTERNAL_SERVER_ERROR.value()
            )
        }
    }
}