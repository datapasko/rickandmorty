package es.smarting.rickmortyapp.domain.usecase

import es.smarting.rickmortyapp.domain.Repository
import es.smarting.rickmortyapp.domain.model.CharacterModel

class GetRandomCharacter(
    private val repository: Repository
) {
    suspend operator fun invoke () : CharacterModel {
        val random = (1..826).random()
        return repository.getSingleCharacter(random.toString())
    }
}