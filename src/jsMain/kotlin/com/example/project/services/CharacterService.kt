@file:Suppress("ktlint:standard:no-wildcard-imports")

package com.example.project.services

import com.example.project.model.CharacterModel
import com.example.project.results.FireMessageResult
import com.example.project.results.MessageResult
import io.kvision.rest.*
import kotlinx.browser.window
import kotlinx.coroutines.asDeferred
import kotlinx.coroutines.await
import kotlinx.serialization.json.Json
import org.w3c.fetch.*
import org.w3c.files.File
import org.w3c.xhr.FormData
import kotlin.js.Promise

object CharacterService {
    private val restClient = RestClient()

    /**
     * Esta propiedad realiza una llamada a la API para obtener una lista de personajes.
     * Utiliza el cliente REST para realizar una solicitud HTTP GET al endpoint
     * proporcionado y devuelve una promesa con el resultado.
     *
     *  @property characters Una promesa que devuelve una lista de objetos [CharacterModel] al completarse.
     *  @see CharacterModel
     */
    val characters: Promise<List<CharacterModel>> =
        restClient.call("http://192.168.1.8:8084/api/character/list") {
            method = HttpMethod.GET
        }

    /**
     * Realiza una solicitud HTTP POST para crear un nuevo personaje.
     * Envía los datos de un personaje al servidor y espera la respuesta con un resultado.
     * @param character El objeto [CharacterModel] que contiene los datos del personaje a crear.
     * @return Un objeto [RestResponse] que envuelve un [MessageResult] con el estado de la operación.
     */
    suspend fun postCharacter(character: CharacterModel): RestResponse<MessageResult> {
        val characterPost: RestResponse<MessageResult> =
            restClient
                .request<MessageResult, CharacterModel>("http://localhost:8084/api/character/create-character", data = character) {
                    method = HttpMethod.POST
                }.asDeferred()
                .await()

        return characterPost
    }

    /**
     * Realiza una solicitud HTTP PUT para actualizar un personaje existente.
     * Envía los datos de un personaje junto con su ID al servidor para actualizar su información.
     *
     * @param character El objeto [CharacterModel] que contiene los nuevos datos del personaje.
     * @param id El identificador único del personaje que se desea actualizar.
     * @return Un objeto [RestResponse] que envuelve un [MessageResult] con el resultado de la operación.
     */
    suspend fun putCharacter(
        character: CharacterModel,
        id: Int,
    ): RestResponse<MessageResult> {
        val characterPut: RestResponse<MessageResult> =
            restClient
                .request<MessageResult, CharacterModel>("http://localhost:8084/api/character/update-character/$id", data = character) {
                    method = HttpMethod.PUT
                }.asDeferred()
                .await()

        return characterPut
    }

    /**
     * Realiza una solicitud HTTP PUT para actualizar un personaje existente.
     * Envía los datos de un personaje junto con su ID al servidor para actualizar su información.
     *
     * @param id El identificador único del personaje que se desea actualizar.
     * @return Un objeto [RestResponse] que envuelve un [MessageResult] con el resultado de la operación.
     */
    suspend fun deleteCharacter(id: Int): RestResponse<MessageResult> {
        val characterDelete: RestResponse<MessageResult> =
            restClient
                .request<MessageResult>("http://localhost:8084/api/character/delete-character/$id") {
                    method = HttpMethod.DELETE
                }.asDeferred()
                .await()

        return characterDelete
    }

    /**
     * Realiza una solicitud HTTP POST para subir una imagen asociada a un personaje.
     * Utiliza un formulario `FormData` para enviar el archivo de la imagen al servidor.
     *
     * @param file El archivo de imagen que se va a subir para el personaje.
     * @return Un objeto [FireMessageResult] que contiene el resultado de la operación de carga.
     * @throws Exception Si ocurre un error durante la solicitud o si la respuesta del servidor no es exitosa.
     */
    suspend fun uploadImageCharacter(file: File): FireMessageResult {
        val formdata = FormData()
        formdata.append("file", file)

        val promise =
            window
                .fetch(
                    "http://localhost:8084/api/character/image",
                    RequestInit(method = "POST", body = formdata, mode = RequestMode.CORS, headers = js("{}")),
                ).await()
        if (promise.ok) {
            val jsonrespuest = promise.text().await()
            return Json.decodeFromString<FireMessageResult>(jsonrespuest)
        } else {
            throw Exception("Error al subir el archivo: ${promise.statusText}")
        }
    }


}
