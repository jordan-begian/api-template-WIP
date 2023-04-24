package com.jordanbegian.apitemplate.exception

import org.springframework.boot.autoconfigure.web.WebProperties
import org.springframework.boot.autoconfigure.web.reactive.error.AbstractErrorWebExceptionHandler
import org.springframework.boot.web.error.ErrorAttributeOptions
import org.springframework.boot.web.reactive.error.ErrorAttributes
import org.springframework.context.ApplicationContext
import org.springframework.http.codec.ServerCodecConfigurer
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.*
import reactor.core.publisher.Mono

@Component
class ProjectExceptionHandler(
    errorAttributes: ErrorAttributes, resources: WebProperties.Resources,
    applicationContext: ApplicationContext, configurer: ServerCodecConfigurer
) : AbstractErrorWebExceptionHandler(errorAttributes, resources, applicationContext) {
    init {
        setMessageReaders(configurer.readers)
        setMessageWriters(configurer.writers)
    }

    override fun getRoutingFunction(errorAttributes: ErrorAttributes): RouterFunction<ServerResponse> {
        return RouterFunctions.route(RequestPredicates.all(), this::renderException)
    }

    private fun renderException(request: ServerRequest): Mono<ServerResponse> {
        return Mono.just(request).map { this.getErrorAttributes(it, ErrorAttributeOptions.defaults()) }
            .flatMap { error ->
                val responseStatusCode = error["statusCode"] as Int
                error.let {
                    it.remove("status")
                    it.remove("requestId")
                    it.remove("statusCode")
                }
                ServerResponse.status(responseStatusCode).json().bodyValue(error)
            }
    }
}