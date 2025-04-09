package es.smarting.rickmortyapp.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import es.smarting.rickmortyapp.domain.Repository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CharacterDetailViewModel(
    private val id: Int,
    private val repository: Repository
): ViewModel() {

    private val _state = MutableStateFlow(CharacterDetailState())
    val state: StateFlow<CharacterDetailState> = _state

    init {
        getCharacter()
    }

    private fun getCharacter() {
        viewModelScope.launch {
            val character = repository.getSingleCharacter(id.toString()).getOrNull()
            _state.update { state ->
                state.copy (
                    character = character
                )
            }

            character?.let {
                getEpisodes(it.episode)
            }
        }
    }

    private fun getEpisodes(episodes: List<String>) {
        viewModelScope.launch {
            val result = repository.getEpisodeByCharacter(episodes).getOrNull()
            _state.update { state ->
                state.copy(episodes = result)
            }
        }
    }

}