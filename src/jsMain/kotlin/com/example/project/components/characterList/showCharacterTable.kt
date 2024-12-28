@file:Suppress("ktlint:standard:filename", "ktlint:standard:no-wildcard-imports")

package com.example.project.components.characterList


import com.example.project.components.characterForm.characterFormCreate
import com.example.project.components.characterForm.characterFormUpdate
import com.example.project.results.MessageResult
import com.example.project.services.CharacterService
import com.example.project.services.CharacterService.deleteCharacter
import io.kvision.core.*
import io.kvision.html.*
import io.kvision.modal.Alert
import io.kvision.modal.Confirm
import io.kvision.panel.Root
import io.kvision.rest.RestResponse
import io.kvision.utils.px
import kotlinx.browser.window
import kotlinx.coroutines.*


@OptIn(DelicateCoroutinesApi::class)
fun showCharacterTable(root: Root) {
    root.apply {
        div(align = Align.CENTER) {
            h3 {
                content = "Lista de Personajes"
            }
        }

        div(className = "container") {
            button("Ingresar Nuevo Personaje", style = ButtonStyle.SUCCESS).onClick {
                characterFormCreate(this)
            }
            padding = 9.px
        }

        MainScope().launch {
            try {
                val characterList = CharacterService.characters.await()

                div(className = "container text-center") {
                    val totalRows = characterList.size / 4 + if (characterList.size % 4 > 0) 1 else 0

                    for (i in 0 until totalRows) {
                        div(className = "row align-items-stretch img-row") {
                            val startIndex = i * 4
                            val endIndex = minOf((i + 1) * 4, characterList.size)

                            characterList.slice(startIndex until endIndex).forEach { character ->
                                div(className = "col-12 col-sm-6 col-md-6 col-lg-3 mb-3") {
                                    div(className = "card h-100") {
                                        h3(className = "card-header") {
                                            +character.name
                                        }
                                        div(className = "card-body") {
                                            maxHeight = 270.px
                                            background =
                                                Background(
                                                    image = "https://web.dragonball-api.com/images-compress/89980.webp",
                                                    repeat = BgRepeat.NOREPEAT,
                                                    size = BgSize.COVER,
                                                )
                                            if (character.image != null) {
                                                image("${character.image}", className = "card-image-top img-fluid img-dragon") {
                                                    height = 250.px
                                                }
                                            } else {
                                                image(
                                                    "https://web.dragonball-api.com/images-compress/logo_dragonballapi.webp",
                                                    className = "card-image-top img-fluid w-100",
                                                )
                                            }
                                        }
                                        div(className = "card-body") {
                                            textAlign = TextAlign.LEFT
                                            span (className = "card-subtitle") {
                                                content = character.name
                                                h6(className = "card-text") {
                                                    colorHex = 0xFBC02D
                                                    content = "Nombre: "
                                                }
                                            }
                                            p(className = "card-text") {
                                                small(className = "text-muted") {
                                                    content = character.desc
                                                }
                                            }
                                            span(className = "text-muted") {
                                                content = "${character.age}"
                                                p(className = "card-text") {
                                                    colorHex = 0xFBC02D
                                                    content = "Edad: "
                                                }
                                            }
                                        }

                                        div(className = "card-footer") {
                                            div(className = "row") {
                                                div(className = "col-12") {
                                                    textAlign = TextAlign.LEFT
                                                    span(className = "col-3 text-muted") {
                                                        content = "${character.powerLevel}"
                                                        h6(className = "h6-text") {
                                                            colorHex = 0xFBC02D
                                                            content = "Power Level: "
                                                        }
                                                    }
                                                }
                                            }
                                            div(className = "row") {
                                                div(className = "col-12") {
                                                    div(className = "btn-group") {
                                                        button("", style = ButtonStyle.OUTLINEDARK, icon = "fas fa-trash") {
                                                            height = 25.px
                                                            fontSize = 10.px
                                                            onClick {
                                                                try {
                                                                    Confirm.show(
                                                                        "Confirma la acción",
                                                                        "Está seguro de eliminar el personaje ${character.name} ?",
                                                                        animation = true,
                                                                        align = Align.LEFT,
                                                                        yesTitle = "Yes",
                                                                        noTitle = "No",
                                                                        noCallback = {
                                                                            Alert.show("Resultado", "El personaje no se eliminó.")
                                                                        },
                                                                    ) {
                                                                        GlobalScope.launch {
                                                                            val status: RestResponse<MessageResult>? =
                                                                                character.id?.let { it1 ->
                                                                                    deleteCharacter(
                                                                                        it1,
                                                                                    )
                                                                                }

                                                                            if (status != null) {
                                                                                if (status.data.status) {
                                                                                    Alert.show(
                                                                                        "Resultado",
                                                                                        "El personaje se ha eliminado.",
                                                                                    ) {
                                                                                        window.location.reload()
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                } catch (e: Exception) {
                                                                    println("El error al eliminar el personaje : ${e.message}")
                                                                }
                                                            }
                                                        }
                                                        button("", icon = "fas fa-edit", style = ButtonStyle.OUTLINESUCCESS) {
                                                            height = 25.px
                                                            fontSize = 10.px
                                                            onClick {
                                                                println("El Personaje es ${character.id}")
                                                                character.id?.let { it1 ->
                                                                    characterFormUpdate(
                                                                        this,
                                                                        character,
                                                                        it1,
                                                                    )
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            } catch (error: Throwable) {
                println("Error al obtener los personajes: $error")
            }
        }
    }
}


