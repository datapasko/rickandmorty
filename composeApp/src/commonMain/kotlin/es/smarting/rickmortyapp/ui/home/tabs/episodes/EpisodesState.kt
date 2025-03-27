package es.smarting.rickmortyapp.ui.home.tabs.episodes

import app.cash.paging.PagingData
import es.smarting.rickmortyapp.domain.model.EpisodeModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class EpisodesState(
    val episodes: Flow<PagingData<EpisodeModel>> = emptyFlow(),
    val playVideo: String = ""
)
