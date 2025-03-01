package es.smarting.rickmortyapp.data

import es.smarting.rickmortyapp.data.remote.ApiService
import es.smarting.rickmortyapp.domain.Repository
import es.smarting.rickmortyapp.domain.model.CharacterModel

class RepositoryImpl(private val api:ApiService):Repository {
    override suspend fun getSingleCharacter(id: String): CharacterModel {
        return api.getSingleCharacter(id).toDomain()
    }
}