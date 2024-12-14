package com.example.project.components.Navbar

import io.kvision.core.Color
import io.kvision.dropdown.dropDown
import io.kvision.form.check.checkBox
import io.kvision.form.text.text
import io.kvision.html.*
import io.kvision.navbar.*
import io.kvision.panel.Root
import io.kvision.utils.px
import kotlinx.browser.document

fun showNavBar(root: Root){
    root.apply {
        navbar("DRAGON BALL Z API") {
            fontFamily = "Bangers"
            nav(rightAlign = true) {
                span {
                    icon("fas fa-dragon"){
                        colorHex = 0xFFA500
                        height = 30.px
                        width = 30.px
                    }
                }
            }
        }
        div (align = Align.CENTER){
            div {
                image("https://web.dragonball-api.com/images-compress/logo_dragonballapi.webp", centered = true){
                    height = 130.px
                }
            }
            h3 {
                content = "Lista de Personajes"
            }
        }
    }
}