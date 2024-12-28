package com.example.project.components.CharacterForm

import com.example.project.services.CharacterService.uploadImageCharacter
import io.kvision.core.Container
import io.kvision.core.onChange
import io.kvision.core.onEvent
import io.kvision.core.onInput
import io.kvision.html.InputType
import io.kvision.html.input
import kotlinx.browser.document
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.events.Event
import org.w3c.files.File
import org.w3c.files.get


fun CharacterFormUploadFile(root: Container){
    root.apply {
        input(type = InputType.FILE, className = "upload-file") {
            id = "upload-file"
            accept = "image/*"
            name = "upload-file"
        }
    }
}
