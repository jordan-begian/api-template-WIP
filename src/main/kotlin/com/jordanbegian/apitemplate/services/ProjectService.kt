package com.jordanbegian.apitemplate.services

import com.jordanbegian.apitemplate.models.ProjectData
import com.jordanbegian.apitemplate.repository.ProjectRepository
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class ProjectService(
    private val projectRepository: ProjectRepository
) {
    fun submitRequest(request: ProjectData): Mono<ProjectData> = projectRepository.submit(request)

    fun retrieveAll(): Mono<List<ProjectData>> = projectRepository.retrieveAll()

    fun retrieveById(id: String): Mono<ProjectData> = projectRepository.retrieveById(id)
}