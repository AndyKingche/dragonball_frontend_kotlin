package com.example.project.model

import kotlinx.serialization.Serializable

@Serializable
data class CharacterModel(
    val id: Int?=null,
    val name: String,
    val desc: String,
    val age: Int,
    var image: String?=null,
    val powerLevel: Int
)
