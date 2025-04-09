package es.smarting.rickmortyapp.domain.model

import es.smarting.rickmortyapp.data.database.entity.CharacterEntity
import kotlinx.serialization.json.Json

data class CharacterOfTheDayModel (
    val characterModel: CharacterModel,
    val selectedDay: String
) {
    fun toEntity(): CharacterEntity {
        return CharacterEntity(
            id = characterModel.id,
            isAlive = characterModel.isAlive,
            name = characterModel.name,
            image = characterModel.image,
            selectedDay = selectedDay,
            species = characterModel.species,
            gender = characterModel.gender,
            origin = characterModel.origin,
            episode = Json.encodeToString(characterModel.episode)
        )
    }
}