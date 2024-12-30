package com.example.project.results

import kotlinx.serialization.Serializable

/**
 * Modelo de respuesta que contiene información sobre el resultado de una operación.
 *
 * Esta clase se utiliza para representar la respuesta de una API que incluye un mensaje descriptivo,
 * el método utilizado para la operación, y el estado de la operación.
 *
 * @param message El mensaje descriptivo que explica el resultado de la operación. Este campo es obligatorio.
 * @param method El nombre del método que se utilizó para la operación. Este campo es obligatorio.
 * @param status El estado de la operación. Indica si la operación fue exitosa (`true`) o no (`false`).
 *               Este campo es obligatorio.
 */
@Serializable
data class MessageResult(
    val message: String,
    val method: String,
    val status: Boolean
)
