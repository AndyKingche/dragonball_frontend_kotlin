package com.example.project

import com.example.project.components.CharacterList.showCharacterTable
import com.example.project.components.Navbar.showNavBar
import io.kvision.*
import io.kvision.html.div
import io.kvision.i18n.DefaultI18nManager
import io.kvision.i18n.I18n
import io.kvision.i18n.tr
import io.kvision.panel.root
import io.kvision.theme.Theme
import io.kvision.theme.ThemeManager
import kotlinx.browser.document


class App : Application() {

    init {
        ThemeManager.init(initialTheme = Theme.DARK, remember = false)
        require("css/bootstrap.min.css")

    }

    override fun start() {
        I18n.manager =
            DefaultI18nManager(
                mapOf(
                    "pl" to require("i18n/messages-pl.json"),
                    "en" to require("i18n/messages-en.json")
                )
            )

        root("kvapp") {
            showNavBar(this)
            //div(tr("Vane Te amo "))
            showCharacterTable(this)

        }

    }
}

fun main() {
    startApplication(
        ::App,
        module.hot,
        DatetimeModule,
        RichTextModule,
        TomSelectModule,
        ImaskModule,
        ToastifyModule,
        FontAwesomeModule,
        BootstrapModule,
        BootstrapIconsModule,
        PrintModule,
        ChartModule,
        TabulatorModule,
        TabulatorCssBootstrapModule,
        MapsModule,
        MaterialModule,
        CoreModule,

    )

}
