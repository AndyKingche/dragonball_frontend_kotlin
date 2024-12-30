package com.example.project.model

import kotlinx.serialization.Serializable

/**
 * Modelo de datos para un personaje de la API de Dragon Ball.
 *
 * Esta clase contiene la información relevante de un personaje, como su nombre, descripción, edad,
 * nivel de poder y una imagen asociada. El campo `id` es opcional y se establece automáticamente por
 * la API cuando se crea o se actualiza un personaje.
 *
 * @param id El identificador único del personaje. Este campo es opcional y puede ser nulo, ya que es asignado
 *           por el servidor en caso de que se esté recuperando o actualizando el personaje.
 * @param name El nombre del personaje.
 * @param desc La descripción del personaje.
 * @param age La edad del personaje.
 * @param image La URL de la imagen del personaje. Este campo es opcional y puede ser nulo si el personaje
 *              no tiene una imagen asociada.
 * @param powerLevel El nivel de poder del personaje, representado como un número entero.
 */
@Suppress("ktlint:standard:class-naming")
@Serializable
data class CharacterModel(
    val id: Int? = null,
    val name: String,
    val desc: String,
    val age: Int,
    var image: String? = null,
    val powerLevel: Int
)
