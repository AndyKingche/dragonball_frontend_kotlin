package com.example.project.services

import com.example.project.model.CharacterModel
import com.example.project.model.MessageResult
import io.kvision.rest.*
import io.kvision.state.ObservableValue
import io.kvision.utils.obj
import kotlinx.coroutines.asDeferred
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.*
import kotlin.js.Promise

object CharacterService {
    private val restClient = RestClient()


    val characters: Promise<List<CharacterModel>> = restClient.call("http://localhost:8084/api/character/list"){
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


}