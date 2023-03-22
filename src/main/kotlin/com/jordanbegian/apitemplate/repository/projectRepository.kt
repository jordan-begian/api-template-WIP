package com.jordanbegian.apitemplate.repository

import com.jordanbegian.apitemplate.models.ProjectData
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.ReactiveMongoTemplate
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

@Repository
class ProjectRepository(@Autowired val template: ReactiveMongoTemplate){

    fun submit(projectData: ProjectData): Mono<ProjectData> {
        return template.insert(projectData)
    }
}

