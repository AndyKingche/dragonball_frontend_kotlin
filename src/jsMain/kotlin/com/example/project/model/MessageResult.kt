package com.example.project.model

import kotlinx.serialization.Serializable

@Serializable
data class MessageResult(
    val message: String,
    val method : String,
    val status: Boolean
)
