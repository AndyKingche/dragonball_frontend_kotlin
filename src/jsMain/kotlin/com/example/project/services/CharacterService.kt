package com.example.project.services

import com.example.project.model.CharacterModel
import com.example.project.results.FireMessageResult
import com.example.project.results.MessageResult
import io.kvision.chart.toJs
import io.kvision.rest.*
import io.kvision.utils.obj
import kotlinx.browser.window
import kotlinx.coroutines.asDeferred
import kotlinx.coroutines.await
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromDynamic
import org.w3c.dom.Text
import org.w3c.fetch.*
import org.w3c.files.File
import org.w3c.xhr.FormData
import kotlin.js.Promise

object CharacterService {
    private val restClient = RestClient()

    val characters: Promise<List<CharacterModel>> = restClient.call("http://192.168.1.8:8084/api/character/list"){
        method = HttpMethod.GET
    }

     suspend fun postCharacter(character: CharacterModel): RestResponse<MessageResult>{

        val characterPost: RestResponse<MessageResult> = restClient.request<MessageResult,CharacterModel>("http://localhost:8084/api/character/create-character", data = character ){
            method = HttpMethod.POST
        }.asDeferred().await()

         return characterPost
    }

    suspend fun putCharacter(character: CharacterModel, id:Int): RestResponse<MessageResult>{

        val characterPut: RestResponse<MessageResult> = restClient.request<MessageResult,CharacterModel>("http://localhost:8084/api/character/update-character/${id}", data = character ){
            method = HttpMethod.PUT
        }.asDeferred().await()

        return characterPut
    }
    suspend fun deleteCharacter(id:Int): RestResponse<MessageResult>{

        val characterDelete: RestResponse<MessageResult> = restClient.request<MessageResult>("http://localhost:8084/api/character/delete-character/${id}"){
            method = HttpMethod.DELETE
        }.asDeferred().await()

        return characterDelete
    }


    suspend fun uploadImageCharacter(file: File): FireMessageResult {
        val formdata = FormData()
        formdata.append("file",file)

        val promise = window.fetch("http://localhost:8084/api/character/image", RequestInit(method = "POST", body = formdata, mode = RequestMode.CORS, headers = js("{}"))).await()
            if(promise.ok){
                val jsonrespuest = promise.text().await()
                return Json.decodeFromString<FireMessageResult>(jsonrespuest)
            }else{
                throw Exception("Error al subir el archivo: ${promise.statusText}")
            }

    }


}