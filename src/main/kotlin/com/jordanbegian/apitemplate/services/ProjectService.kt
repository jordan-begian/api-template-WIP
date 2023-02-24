package com.jordanbegian.apitemplate.services

import com.jordanbegian.apitemplate.models.ProjectData
import com.jordanbegian.apitemplate.models.ProjectRequest
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono
import java.util.*

@Component
class ProjectService {
    fun submitRequest(request: ProjectRequest): Mono<ProjectData> {
        return ProjectData(UUID.randomUUID().toString(), request.field).toMono()
    }
}