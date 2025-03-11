package es.smarting.rickmortyapp.domain

import app.cash.paging.PagingData
import es.smarting.rickmortyapp.data.database.entity.CharacterEntity
import es.smarting.rickmortyapp.domain.model.CharacterModel
import es.smarting.rickmortyapp.domain.model.CharacterOfTheDayModel
import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun getSingleCharacter(id:String): CharacterModel
    fun getAllCharacters(): Flow<PagingData<CharacterModel>>
    suspend fun getCharacterDB(): CharacterOfTheDayModel?
    suspend fun saveCharacter(characterOfTheDayModel: CharacterOfTheDayModel)
}