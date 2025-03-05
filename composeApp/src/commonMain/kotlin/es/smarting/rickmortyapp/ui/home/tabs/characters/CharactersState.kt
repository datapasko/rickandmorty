package es.smarting.rickmortyapp.ui.home.tabs.characters

import app.cash.paging.PagingData
import es.smarting.rickmortyapp.domain.model.CharacterModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class CharactersState(
    val characterOfDay: CharacterModel? = null,
    val characters: Flow<PagingData<CharacterModel>> = emptyFlow()
)
