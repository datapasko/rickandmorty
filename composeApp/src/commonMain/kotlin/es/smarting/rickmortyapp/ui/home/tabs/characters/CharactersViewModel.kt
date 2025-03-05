package es.smarting.rickmortyapp.ui.home.tabs.characters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import es.smarting.rickmortyapp.domain.Repository
import es.smarting.rickmortyapp.domain.usecase.GetRandomCharacter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CharactersViewModel(
    val getRandomCharacter: GetRandomCharacter,
    private val repository: Repository
): ViewModel() {

    private val _state = MutableStateFlow<CharactersState>(CharactersState())
    val state: StateFlow<CharactersState> = _state

    init {
        getCharacterOfDay()
        getAllCharacters()
    }

    private fun getCharacterOfDay() {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                getRandomCharacter()
            }
            _state.update { state -> state.copy(characterOfDay = result) }
        }
    }

    private fun getAllCharacters() {
        _state.update {
            it.copy(
                characters = repository.getAllCharacters()
            )
        }
    }
}