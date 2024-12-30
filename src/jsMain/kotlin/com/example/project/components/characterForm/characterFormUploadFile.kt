@file:Suppress("ktlint:standard:filename")

package com.example.project.components.characterForm

import io.kvision.core.Container
import io.kvision.html.InputType
import io.kvision.html.input

/**
 * Crea un campo de formulario para la carga de archivos de imagen, permitiendo al usuario seleccionar una imagen
 * que se enviará como parte del formulario de creación o actualización de un personaje.
 *
 * Esta función agrega un campo de tipo archivo (`<input type="file">`) al contenedor proporcionado, con las siguientes características:
 * - **id**: El identificador del campo de entrada se establece como "upload-file".
 * - **accept**: El campo está limitado a aceptar únicamente archivos de imagen (tipos de archivo como .jpg, .png, etc.).
 * - **name**: El nombre del campo de entrada se establece como "upload-file", para que pueda ser identificado en el servidor al enviar el formulario.
 *
 * @param root El contenedor [Container] donde se añadirá el campo de entrada de archivo.
 *
 * Este campo de entrada permite al usuario seleccionar un archivo de imagen para subirlo. Es parte de un formulario
 * más grande que puede incluir otros campos como nombre, edad y descripción de un personaje.
 */
fun characterFormUploadFile(root: Container) {
    root.apply {
        input(type = InputType.FILE, className = "upload-file") {
            id = "upload-file"
            accept = "image/*"
            name = "upload-file"
        }
    }
}
