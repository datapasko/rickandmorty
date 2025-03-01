package es.smarting.rickmortyapp.data.remote.response

import es.smarting.rickmortyapp.domain.model.CharacterModel
import kotlinx.serialization.Serializable

@Serializable
data class CharacterResponse(
    val id: String,
    val status: String,
    val image: String
) {
    fun toDomain(): CharacterModel {
        return CharacterModel(
            id = id,
            image = image,
            isAlive = status.lowercase() == "alive"
        )
    }
}
