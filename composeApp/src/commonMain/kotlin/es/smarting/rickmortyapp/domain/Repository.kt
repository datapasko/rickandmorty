package es.smarting.rickmortyapp.domain

import es.smarting.rickmortyapp.domain.model.CharacterModel

interface Repository {
    suspend fun getSingleCharacter(id:String): CharacterModel
}