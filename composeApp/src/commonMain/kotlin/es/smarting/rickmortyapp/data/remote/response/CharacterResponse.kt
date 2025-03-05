package es.smarting.rickmortyapp.data.remote.response

import es.smarting.rickmortyapp.domain.model.CharacterModel
import kotlinx.serialization.Serializable

@Serializable
data class CharacterResponse(
    val id: Int,
    val status: String,
    val image: String,
    val name: String
) {
    fun toDomain(): CharacterModel {
        return CharacterModel(
            id = id,
            isAlive = status.lowercase() == "alive",
            image = image,
            name = name
        )
    }
}
