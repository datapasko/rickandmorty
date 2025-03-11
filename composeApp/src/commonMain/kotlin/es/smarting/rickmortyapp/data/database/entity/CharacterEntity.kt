package es.smarting.rickmortyapp.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import es.smarting.rickmortyapp.domain.model.CharacterModel
import es.smarting.rickmortyapp.domain.model.CharacterOfTheDayModel

@Entity(tableName = "character")
data class CharacterEntity (
    @PrimaryKey
    val id: Int,
    val isAlive: Boolean,
    val image: String,
    val name: String,
    val selectedDay: String
) {
    fun toDomain(): CharacterOfTheDayModel {
        return CharacterOfTheDayModel(
            characterModel = CharacterModel(
                id = id,
                isAlive = isAlive,
                image = image,
                name = name
            ),
            selectedDay = selectedDay
        )
    }
}