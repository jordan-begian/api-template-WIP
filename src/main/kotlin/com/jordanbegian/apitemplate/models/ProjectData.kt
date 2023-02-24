package com.jordanbegian.apitemplate.models

import java.io.Serializable


data class ProjectData(
    val id: String,
    val field: String = ""
) : Serializable