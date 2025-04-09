package es.smarting.rickmortyapp.data.remote.response

import es.smarting.rickmortyapp.domain.model.CharacterModel
import kotlinx.serialization.Serializable

@Serializable
data class CharacterResponse(
    val id: Int,
    val status: String,
    val image: String,
    val name: String,
    val species: String,
    val gender: String,
    val origin: OriginResponse,
    val episode: List<String>
) {
    fun toDomain(): CharacterModel {
        return CharacterModel(
            id = id,
            isAlive = status.lowercase() == "alive",
            image = image,
            name = name,
            species = species,
            gender = gender,
            origin = origin.name,
            episode = episode.map { it.substringAfterLast("/") }
        )
    }
}
