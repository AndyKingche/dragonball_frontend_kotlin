package com.example.project.components.CharacterForm

import com.example.project.model.CharacterModel
import com.example.project.model.MessageResult
import com.example.project.services.CharacterService.postCharacter
import io.kvision.form.form
import io.kvision.form.text.text
import io.kvision.form.text.textArea
import io.kvision.html.Button
import io.kvision.html.ButtonType
import io.kvision.html.InputType
import io.kvision.html.div
import io.kvision.modal.Modal
import io.kvision.rest.RestResponse
import io.kvision.utils.px
import kotlinx.browser.window
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

fun CharacterFormUpdate(root: Button, character: CharacterModel,id: Int) {

    root.apply {

        val modal = Modal("Actualiza el Personaje ${character.name}", true, animation = true, escape = true) {

            form {

                div(className = "mb-3") {
                    nameInput = text( InputType.TEXT,label = "Nuevo Personaje"){
                        value = character.name
                        placeholder = "Nuevo Personaje"
                    }
                }

                div(className = "mb-3") {
                    ageInput = text( InputType.NUMBER, label = "Edad") {
                        width = 100.px
                        placeholder = "Edad"
                    }

                }
                div(className = "mb-3") {

                    descInput = textArea(rows = 3, label = "Descripcion") {
                        placeholder = "Descripcion"
                    }
                }

                div(className = "mb-3") {
                    powerLevelInput = text(InputType.NUMBER, label = "Power Level") {
                        width = 100.px
                        placeholder = "Power Level"
                    }

                }
            }
        }

        modal.addButton(button = Button("Guardar", type = ButtonType.SUBMIT, className = "btn btn-warning").apply {
            onClick {
                it.preventDefault()
                println(nameInput?.value.orEmpty())
                println(ageInput?.value.orEmpty())
                println(descInput?.value.orEmpty())
                println(powerLevelInput?.value.orEmpty())

                val newCharacterModel = CharacterModel(
                    name = nameInput?.value.orEmpty(),
                    age = ageInput?.value.orEmpty().toInt(),
                    desc = descInput?.value.orEmpty(),
                    powerLevel = powerLevelInput?.value.orEmpty().toInt(),
                )

                GlobalScope.launch {
                    try {
                        val status : RestResponse<MessageResult> =  postCharacter(newCharacterModel)
                        println(status)

                        if(status.data.status){
                            window.location.reload()
                        }
                    }catch (e: Exception){
                        println("El error al crear el personaje : ${e.message}")
                    }
                }


                modal.hide()

            }


        })

        modal.show()

    }
}