package es.smarting.rickmortyapp.domain

import app.cash.paging.PagingData
import es.smarting.rickmortyapp.domain.model.CharacterModel
import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun getSingleCharacter(id:String): CharacterModel
    fun getAllCharacters(): Flow<PagingData<CharacterModel>>
}