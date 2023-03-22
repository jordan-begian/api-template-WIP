package com.jordanbegian.apitemplate.handlers

import com.jordanbegian.apitemplate.models.ProjectData
import org.springframework.web.reactive.function.server.ServerRequest
import com.jordanbegian.apitemplate.services.ProjectService
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.bodyToMono
import org.springframework.web.reactive.function.server.json
import reactor.core.publisher.Mono

@Component
class ProjectHandlers(
    private val projectService: ProjectService
) {
    fun submitRequest(serverRequest: ServerRequest): Mono<ServerResponse> = serverRequest.bodyToMono<ProjectData>()
        .flatMap { projectService.submitRequest(it) }
        .flatMap { ServerResponse.ok().json().bodyValue(it) }
}