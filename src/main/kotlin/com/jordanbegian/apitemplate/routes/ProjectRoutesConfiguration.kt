package com.jordanbegian.apitemplate.routes

import com.jordanbegian.apitemplate.handlers.ProjectHandlers
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.router

@Component
class ProjectRoutesConfiguration {
    @Bean
    fun projectRoutes(projectHandlers: ProjectHandlers): RouterFunction<ServerResponse> {
        return router {
            POST("/submit", projectHandlers::submitRequest)
        }
    }
}
