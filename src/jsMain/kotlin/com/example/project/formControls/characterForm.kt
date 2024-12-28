package com.example.project.formControls

import io.kvision.types.KFile
import kotlinx.serialization.Serializable

@Serializable
data class characterForm(
    val name: String? = null,
    val desc: String? = null,
    val age: Int? = null,
    var image: List<KFile>? = null,
    val powerLevel: Int? = null
)
