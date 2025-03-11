package es.smarting.rickmortyapp.domain.usecase

import es.smarting.rickmortyapp.data.database.entity.CharacterEntity
import es.smarting.rickmortyapp.domain.Repository
import es.smarting.rickmortyapp.domain.model.CharacterModel
import es.smarting.rickmortyapp.domain.model.CharacterOfTheDayModel
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

class GetRandomCharacter(
    private val repository: Repository
) {
    suspend operator fun invoke () : CharacterModel {

        val characterEntity: CharacterOfTheDayModel? = repository.getCharacterDB()
        val selectedDay = getCurrentDayOfYear()

        return if(characterEntity != null && characterEntity.selectedDay == selectedDay) {
            characterEntity.characterModel
        } else {
            val result = generateRandomCharacter()
            repository.saveCharacter(CharacterOfTheDayModel(characterModel = result, selectedDay))
            result
        }
    }

    private suspend fun generateRandomCharacter(): CharacterModel {
        val random = (1..826).random()
        return repository.getSingleCharacter(random.toString())
    }

    private fun getCurrentDayOfYear(): String {
        val instant: Instant = Clock.System.now()
        val localTime: LocalDateTime = instant.toLocalDateTime(TimeZone.currentSystemDefault())
        return "${localTime.dayOfYear}${localTime.year}"
    }
}