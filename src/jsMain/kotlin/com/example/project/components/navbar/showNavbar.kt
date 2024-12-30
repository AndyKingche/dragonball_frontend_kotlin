@file:Suppress("ktlint:standard:filename", "ktlint:standard:no-wildcard-imports")

package com.example.project.components.navbar

import io.kvision.html.*
import io.kvision.navbar.*
import io.kvision.panel.Root
import io.kvision.utils.px

/**
 * Muestra la barra de navegación principal para la aplicación, incluyendo el título "DRAGON BALL Z API"
 * y un enlace de navegación para la página de inicio. La barra de navegación es fija en la parte superior
 * de la página (sticky-top) y tiene un estilo transparente con una sombra mínima.
 *
 * También se muestra un logo de la API de Dragon Ball debajo de la barra de navegación, centrado en la pantalla.
 *
 * @param root El contenedor raíz [Root] donde se añadirá la barra de navegación y el logo.
 *
 * La barra de navegación incluye:
 * - Un título "DRAGON BALL Z API" con una tipografía personalizada.
 * - Un enlace de navegación a la página de inicio con el icono de "Dungeons and Dragons" como símbolo.
 * - El logo de la API de Dragon Ball centrado debajo de la barra de navegación.
 */
fun showNavBar(root: Root) {
    root.apply {
        navbar("DRAGON BALL Z API") {
            setAttribute("class", "navbar navbar-expand-lg navbar-transparent shadow-none bg-body-tertiary sticky-top")
            setAttribute("aria-label", "Main navigation")
            fontFamily = "Bangers"

            nav(rightAlign = true) {
                navLink("Home", icon = "fab fa-d-and-d", url = "#") {
                    fontFamily = "Arial"
                    colorHex = 0x77b300 // Color verde para el enlace
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
