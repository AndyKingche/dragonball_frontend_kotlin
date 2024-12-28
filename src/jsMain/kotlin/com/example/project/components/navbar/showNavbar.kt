@file:Suppress("ktlint:standard:filename", "ktlint:standard:no-wildcard-imports")

package com.example.project.components.navbar

import io.kvision.html.*
import io.kvision.navbar.*
import io.kvision.panel.Root
import io.kvision.utils.px

fun showNavBar(root: Root) {
    root.apply {
        navbar("DRAGON BALL Z API") {
            setAttribute("class", "navbar navbar-expand-lg navbar-transparent shadow-none bg-body-tertiary sticky-top")
            setAttribute("aria-label", "Main navigation")
            fontFamily = "Bangers"

            nav(rightAlign = true) {
                navLink("Home", icon = "fab fa-d-and-d", url = "#") {
                    fontFamily = "Arial"
                    colorHex = 0x77b300
                }
            }
        }
        div(align = Align.CENTER) {
            div {
                image("https://web.dragonball-api.com/images-compress/logo_dragonballapi.webp", centered = true) {
                    height = 130.px
                }
            }
        }
    }
}
