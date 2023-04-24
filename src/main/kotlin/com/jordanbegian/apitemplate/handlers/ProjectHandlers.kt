package com.jordanbegian.apitemplate.handlers

import com.jordanbegian.apitemplate.models.ProjectData
import org.springframework.web.reactive.function.server.ServerRequest
import com.jordanbegian.apitemplate.services.ProjectService
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.bodyToMono
import org.springframework.web.reactive.function.server.json
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.switchIfEmpty
import reactor.kotlin.core.publisher.toMono

@Component
class ProjectHandlers(
    private val projectService: ProjectService
) {
    fun submitRequest(serverRequest: ServerRequest): Mono<ServerResponse> = serverRequest.bodyToMono<ProjectData>()
        .flatMap { projectService.submitRequest(it) }
        .flatMap { ServerResponse.ok().json().bodyValue(it) }

    fun retrieveAll(serverRequest: ServerRequest): Mono<ServerResponse> = serverRequest
        .toMono()
        .flatMap {
            projectService.retrieveAll()
        }
        .flatMap { response ->
            ServerResponse.ok().json().bodyValue(response)
        }

    fun retrieveById(serverRequest: ServerRequest): Mono<ServerResponse> = serverRequest
        .pathVariable("project-data-id")
        .toMono()
        .filter { requestId ->
            !requestId.isNullOrBlank()
        }
        .switchIfEmpty { Mono.error(HttpClientErrorException(HttpStatus.BAD_REQUEST, "Please provide an id")) }
        .flatMap { requestId ->
            projectService.retrieveById(requestId)
        }
        .flatMap { projectData ->
            ServerResponse.ok().json().bodyValue(projectData)
        }
}