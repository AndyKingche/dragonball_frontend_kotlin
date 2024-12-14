package com.example.project.components.CharacterList


import com.example.project.components.CharacterForm.CharacterFormCreate
import com.example.project.components.CharacterForm.CharacterFormUpdate
import com.example.project.services.CharacterService
import io.kvision.core.style
import io.kvision.html.*
import io.kvision.panel.Root
import io.kvision.utils.px
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.await
import kotlinx.coroutines.launch



fun showCharacterTable(root: Root){


    root.apply {

        div (className = "container") {
            button("Ingresar Nuevo Personaje", style = ButtonStyle.SUCCESS).onClick {
                CharacterFormCreate(this)
            }
            padding = 9.px
        }


        MainScope().launch {
            try {

                val characterList = CharacterService.characters.await()

                div(className = "container text-center") {

                    val totalRows = characterList.size / 4 + if (characterList.size % 4 > 0) 1 else 0

                    for (i in 0 until totalRows) {
                        div(className = "row align-items-stretch") {

                            val startIndex = i * 4
                            val endIndex = minOf((i + 1) * 4, characterList.size)

                            characterList.slice(startIndex until endIndex).forEach { character ->
                                div(className = "col-3 mb-3") {
                                    div(className = "card h-100") {

                                        h3(className = "card-header") {
                                            +character.name
                                        }
                                        div(className = "card-body") {
                                            h5(className = "card-title") {
                                                content = "Nombre: ${character.name}"
                                            }
                                            h6(className = "card-subtitle text-muted") {
                                                content = "${character.desc}"
                                            }
                                        }

                                        div{
                                            image("https://dragonball-api.com/characters/goku_normal.webp"){
                                                height = 250.px
                                            }
                                        }

                                        div(className = "card-body") {
                                            p(className = "card-text") {
                                                content = "Edad: ${character.age}"
                                            }
                                        }

                                        div(className = "card-footer") {
                                            div(className = "row") {
                                                div(className = "col-12") {
                                                    content = "Power Level: ${character.powerLevel}"
                                                }
                                            }
                                            div(className = "row") {
                                                div(className = "col-12") {
                                                    div(className = "btn-group") {
                                                        button("", style = ButtonStyle.OUTLINEDARK, icon = "fas fa-trash" ) {
                                                            height = 25.px
                                                            fontSize = 10.px
                                                            onClick {
                                                                println("El Personaje es ${character.id}")
                                                            }
                                                        }
                                                        button("",icon = "fas fa-edit",style = ButtonStyle.OUTLINESUCCESS) {
                                                            height = 25.px
                                                            fontSize = 10.px
                                                            onClick {
                                                                println("El Personaje es ${character.id}")
                                                                character.id?.let { it1 ->
                                                                    CharacterFormUpdate(this, character,
                                                                        it1
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

                    // Imprimir los personajes en la consola
                    /*characterList.forEach { character ->
                        println("Son los personajes: ${character.name}")
                    }*/
                }


            } catch (error: Throwable) {
                println("Error al obtener los personajes: $error")
            }
        }


    }
}


