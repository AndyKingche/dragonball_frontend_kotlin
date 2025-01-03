@file:Suppress("ktlint:standard:filename", "ktlint:standard:no-wildcard-imports")

package com.example.project.components.characterForm

import com.example.project.model.CharacterModel
import com.example.project.results.FireMessageResult
import com.example.project.results.MessageResult
import com.example.project.services.CharacterService.postCharacter
import com.example.project.services.CharacterService.putCharacter
import com.example.project.services.CharacterService.uploadImageCharacter
import io.kvision.form.form
import io.kvision.form.text.text
import io.kvision.form.text.textArea
import io.kvision.html.*
import io.kvision.modal.Modal
import io.kvision.rest.RestResponse
import io.kvision.utils.px
import kotlinx.browser.document
import kotlinx.browser.window
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.w3c.dom.HTMLInputElement
import org.w3c.files.get

/**
 * Se crea un formulario modal para actualizar un nuevo personaje, con campos para el nombre, edad, descripción y nivel de poder.
 * Además, permite actualizar y cargar una imagen del personaje y guarda los datos en el servidor.
 *
 * @param root El botón [Button] que al ser presionado desencadenará la apertura del modal y la creación del personaje.
 * @param character El objeto [CharacterModel] que contiene los datos del personaje a crear
 * @param id El identificador único del personaje que se desea actualizar.
 *
 * El formulario incluye los siguientes campos:
 * - **Nuevo Personaje**: Campo de texto para el nombre del personaje.
 * - **Edad**: Campo numérico para la edad del personaje.
 * - **Descripción**: Campo de texto para proporcionar una breve descripción del personaje.
 * - **Power Level**: Campo numérico para el nivel de poder del personaje.
 * - **Imagen**: Opción para subir una imagen del personaje.
 * Nota: Hay una validación de la subida de imágenes en el caso de que un personaje ya tenga una imagen se le preguntará si desea
 * actualizarlo o no.
 *
 * Una vez que el formulario es enviado:
 * 1. Se crea un objeto [CharacterModel] con los valores ingresados en el formulario.
 * 2. Si se ha seleccionado una imagen, esta es subida usando [uploadImageCharacter].
 * 3. Los datos del personaje (incluyendo la imagen si es subida) son enviados al servidor utilizando [postCharacter].
 * 4. Si la creación del personaje es exitosa, la página se recarga.
 *
 * @throws Exception Sí ocurre un error durante la subida de la imagen o al enviar los datos al servidor.
 */

@OptIn(DelicateCoroutinesApi::class)
fun characterFormUpdate(
    root: Button,
    character: CharacterModel,
    id: Int
) {
    var imgCambio: Boolean = false

    root.apply {
        val modal =
            Modal("Actualiza el Personaje ${character.name}", true, animation = true, escape = true) {
                form {
                    div(className = "mb-3") {
                        nameInput =
                            text(InputType.TEXT, label = "Nuevo Personaje") {
                                value = character.name
                                placeholder = "Nuevo Personaje"
                            }
                    }

                    div(className = "mb-3") {
                        ageInput =
                            text(InputType.NUMBER, label = "Edad") {
                                value = character.age.toString()
                                width = 100.px
                                placeholder = "Edad"
                            }
                    }
                    div(className = "mb-3") {
                        descInput =
                            textArea(rows = 3, label = "Descripcion") {
                                value = character.desc
                                placeholder = "Descripcion"
                            }
                    }

                    div(className = "mb-3") {
                        powerLevelInput =
                            text(InputType.NUMBER, label = "Power Level") {
                                value = character.powerLevel.toString()
                                width = 100.px
                                placeholder = "Power Level"
                            }
                    }
                    div(className = "mb-3") {
                        val uploadFileContainer =
                            div(className = "col-12") {
                                characterFormUploadFile(this)
                            }
                        uploadFileContainer.visible = false
                        if (character.image != null) {
                            p(className = "text-muted") {
                                content = "Ten en cuenta que el Personaje ya tiene una imagen guardada en la Base de Datos"
                                link("Cambiar Imagen: ", "#", "fas fa-trash", labelFirst = false) {
                                    onClick {
                                        if (label == "Cambiar Imagen: ") {
                                            label = "No Cambiar Imagen: "
                                            uploadFileContainer.show()
                                            imgCambio = true
                                        } else if (label == "No Cambiar Imagen: ") {
                                            label = "Cambiar Imagen: "
                                            uploadFileContainer.hide()
                                            imgCambio = false
                                        }
                                    }
                                }
                            }
                        } else {
                            characterFormUploadFile(this)
                        }
                    }
                }
            }

        modal.addButton(
            button =
                Button("Actualizar", type = ButtonType.SUBMIT, className = "btn btn-warning").apply {
                    onClick {
                        it.preventDefault()
                        println(nameInput?.value.orEmpty())
                        println(ageInput?.value.orEmpty())
                        println(descInput?.value.orEmpty())
                        println(powerLevelInput?.value.orEmpty())

                        val newCharacterModel =
                            CharacterModel(
                                name = nameInput?.value.orEmpty(),
                                age = ageInput?.value.orEmpty().toInt(),
                                desc = descInput?.value.orEmpty(),
                                powerLevel = powerLevelInput?.value.orEmpty().toInt(),
                            )

                        if (character.image == null || imgCambio) {
                            val inputImage = document.getElementById("upload-file") as HTMLInputElement
                            val file = inputImage.files?.get(0)

                            GlobalScope.launch {
                                try {
                                    if (file != null) {
                                        val imageUpload: FireMessageResult = uploadImageCharacter(file)
                                        if (imageUpload.status) {
                                            newCharacterModel.image = imageUpload.link
                                            val status: RestResponse<MessageResult> = putCharacter(newCharacterModel, id)
                                            if (status.data.status) {
                                                window.location.reload()
                                            }
                                        }
                                    } else {
                                        newCharacterModel.image = character.image
                                        val status: RestResponse<MessageResult> = putCharacter(newCharacterModel, id)
                                        if (status.data.status) {
                                            window.location.reload()
                                        }
                                    }
                                } catch (e: Exception) {
                                    println("El error al actualizar el personaje : ${e.message}")
                                }
                            }
                        } else {
                            GlobalScope.launch {
                                newCharacterModel.image = character.image
                                val status: RestResponse<MessageResult> = putCharacter(newCharacterModel, id)
                                if (status.data.status) {
                                    window.location.reload()
                                }
                            }
                        }
                        modal.hide()
                    }
                },
        )

        modal.show()
    }
}
