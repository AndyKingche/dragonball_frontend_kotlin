@file:Suppress("ktlint:standard:filename", "ktlint:standard:no-wildcard-imports")

package com.example.project.components.footer

import io.kvision.html.*
import io.kvision.panel.Root
import io.kvision.utils.px


fun showFooter(root: Root) {
    root.apply {
        footer(className = "pt-5 pb-3") {
            div(className = "container") {
                div(className = "row justify-content-center text-center text-left align-items-center") {
                    div(className = "col-12 col-md-12 col-xxl-6 px-0") {
                        div(className = "mb-4") {
                            image("img/kingche.png", className = "mb-4 logo-inverse img-fluid") {
                                height = 150.px
                            }
                            p(className = "lead") {
                                fontSize = 15.px
                                content =
                                    "Dragon Ball API: ¡Una versión renovada de un clásico! Esta página web fue creada con la poderosa combinación de Kotlin y Kvision. ¡Goku estaría orgulloso!"
                            }
                        }
                    }
                    hr(className = "mt-6 mb-3")
                    div(className = "row align-items-center") {
                        div(className = "col-lg-3 col-md-6 col-12") {
                            span {
                                span {
                                    content = "© 2024 "
                                }

                                content = "Andy Kingche, Inc. All Rights Reserved"
                            }
                        }
                        div(className = "col-12 col-md-6 col-lg-7 d-lg-flex justify-content-center") {
                            nav(className = "nav nav-footer") {
                                div(className = "nav-link ps-0") {
                                    image("img/kotlin.svg", className = "mb-4 logo-inverse img-fluid") {
                                        height = 20.px
                                    }
                                    p {
                                        content = "Kotlin"
                                    }
                                }
                                div(className = "nav-link px-2 px-md-0") {
                                    image("img/kvision.svg", className = "mb-4 logo-inverse img-fluid") {
                                        height = 20.px
                                    }
                                    p {
                                        content = "Kvision"
                                    }
                                }
                                div(className = "nav-link") {
                                    image("img/spring-boot.webp", className = "mb-4 logo-inverse img-fluid") {
                                        height = 20.px
                                    }
                                    p {
                                        content = "Springboot"
                                    }
                                }
                            }
                        }
                        div(className = "col-lg-2 col-md-12 col-12 d-lg-flex justify-content-end") {
                            add(
                                customTag("a").apply {
                                    setAttribute("href", "https://github.com/AndyKingche")
                                    add(
                                        customTag("svg").apply {
                                            setAttribute("width", "16")
                                            setAttribute("height", "16")
                                            setAttribute("class", "bi bi-github")
                                            setAttribute("view-box", "0 0 16 16")
                                            setAttribute("fill", "currentColor")
                                            add(
                                                customTag("path").apply {
                                                    setAttribute(
                                                        "d",
                                                        @Suppress("ktlint:standard:max-line-length")
                                                        "M8 0C3.58 0 0 3.58 0 8c0 3.54 2.29 6.53 5.47 7.59.4.07.55-.17.55-.38 0-.19-.01-.82-.01-1.49-2.01.37-2.53-.49-2.69-.94-.09-.23-.48-.94-.82-1.13-.28-.15-.68-.52-.01-.53.63-.01 1.08.58 1.23.82.72 1.21 1.87.87 2.33.66.07-.52.28-.87.51-1.07-1.78-.2-3.64-.89-3.64-3.95 0-.87.31-1.59.82-2.15-.08-.2-.36-1.02.08-2.12 0 0 .67-.21 2.2.82.64-.18 1.32-.27 2-.27.68 0 1.36.09 2 .27 1.53-1.04 2.2-.82 2.2-.82.44 1.1.16 1.92.08 2.12.51.56.82 1.27.82 2.15 0 3.07-1.87 3.75-3.65 3.95.29.25.54.73.54 1.48 0 1.07-.01 1.93-.01 2.2 0 .21.15.46.55.38A8.012 8.012 0 0 0 16 8c0-4.42-3.58-8-8-8z")
                                                },
                                            )
                                        },
                                    )
                                    content = "Andy Kingche"
                                },
                            )
                        }
                    }
                }
            }
        }
    }
}

fun Div.hr(
    className: String = "",
    block: CustomTag.() -> Unit = {}
) {
    customTag("hr") {
        setAttribute("class", className)
        block()
    }
}
