package com.jordanbegian.apitemplate

import org.springframework.boot.autoconfigure.web.WebProperties
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component

@Component
class ApplicationConfiguration {
    @Bean
    fun resources(): WebProperties.Resources {
        return WebProperties.Resources()
    }
}