package com.example.project.results

import kotlinx.serialization.Serializable

@Serializable
data class FireMessageResult(
    val link: String? = null,
    val status: Boolean
)
