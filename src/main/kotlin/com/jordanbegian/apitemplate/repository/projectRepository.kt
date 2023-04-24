package com.jordanbegian.apitemplate.repository

import com.jordanbegian.apitemplate.models.ProjectData
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.ReactiveMongoTemplate
import org.springframework.data.mongodb.core.findAll
import org.springframework.data.mongodb.core.findById
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Repository
import org.springframework.web.client.HttpClientErrorException
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.switchIfEmpty

@Repository
class ProjectRepository(@Autowired val template: ReactiveMongoTemplate){

    fun submit(projectData: ProjectData): Mono<ProjectData> {
        return template.insert(projectData)
    }

    fun retrieveById(id: String): Mono<ProjectData> = template.findById<ProjectData>(id)
        .filter { it != null }
        .switchIfEmpty { Mono.error(HttpClientErrorException(HttpStatus.NOT_FOUND, "No entry found for id: $id")) }

    fun retrieveAll(): Mono<List<ProjectData>> = template.findAll<ProjectData>()
        .collectSortedList()
        .filter { it.isNotEmpty() }
        .switchIfEmpty { Mono.error(HttpClientErrorException(HttpStatus.NOT_FOUND, "No entries found")) }
}

