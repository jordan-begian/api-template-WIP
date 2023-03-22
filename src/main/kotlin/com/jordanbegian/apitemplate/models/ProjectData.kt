package com.jordanbegian.apitemplate.models

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.io.Serializable

@Document
data class ProjectData(
    @Id val id: String?,
    val field: String
) : Serializable