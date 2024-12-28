@file:Suppress("ktlint:standard:filename")

package com.example.project.components.characterForm

import io.kvision.core.Container
import io.kvision.html.InputType
import io.kvision.html.input


fun characterFormUploadFile(root: Container) {
    root.apply {
        input(type = InputType.FILE, className = "upload-file") {
            id = "upload-file"
            accept = "image/*"
            name = "upload-file"
        }
    }
}
