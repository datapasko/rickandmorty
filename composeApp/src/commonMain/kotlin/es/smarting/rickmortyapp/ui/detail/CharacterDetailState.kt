package es.smarting.rickmortyapp.ui.detail

import es.smarting.rickmortyapp.domain.model.CharacterModel
import es.smarting.rickmortyapp.domain.model.EpisodeModel

data class CharacterDetailState (
    val character: CharacterModel ?= null,
    val episodes: List<EpisodeModel> ?= null
)