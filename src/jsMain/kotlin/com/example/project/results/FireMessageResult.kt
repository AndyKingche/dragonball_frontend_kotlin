package com.example.project.results

import kotlinx.serialization.Serializable

/**
 * Modelo de respuesta de la API de Firebase al subir una imagen.
 *
 * Esta clase contiene el resultado de la operación de subida de una imagen, que incluye un enlace
 * a la imagen subida (si la subida fue exitosa) y el estado de la operación (si fue exitosa o no).
 *
 * @param link El enlace (URL) de la imagen subida. Este campo es opcional y puede ser nulo si la
 *             operación no fue exitosa o si no se proporcionó un enlace.
 * @param status El estado de la operación. Indica si la subida fue exitosa o no. Este campo es obligatorio.
 */
@Serializable
data class FireMessageResult(
    val link: String? = null,
    val status: Boolean
)
