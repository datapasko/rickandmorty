package es.smarting.rickmortyapp.data.remote

import es.smarting.rickmortyapp.data.remote.response.CharacterResponse
import es.smarting.rickmortyapp.data.remote.response.CharacterWrapperResponse
import es.smarting.rickmortyapp.data.remote.response.EpisodesWrapperResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class ApiService(
    private val client: HttpClient
) {
    suspend fun getSingleCharacter(id: String): CharacterResponse {
        return client.get("/api/character/$id").body()
    }

    suspend fun getAllCharacters(page:Int): CharacterWrapperResponse {
        return client.get("/api/character"){
            parameter("page", page)
        }.body()
    }

    suspend fun getAllEpisodes(page: Int): EpisodesWrapperResponse {
        return client.get("/api/episode"){
            parameter("page", page)
        }.body()
    }
}