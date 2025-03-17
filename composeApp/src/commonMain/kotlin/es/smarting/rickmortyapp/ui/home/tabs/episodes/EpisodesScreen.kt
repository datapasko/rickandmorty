package es.smarting.rickmortyapp.ui.home.tabs.episodes

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import app.cash.paging.compose.collectAsLazyPagingItems
import es.smarting.rickmortyapp.domain.model.EpisodeModel
import es.smarting.rickmortyapp.domain.model.SeasonEpisode
import es.smarting.rickmortyapp.domain.model.SeasonEpisode.*
import es.smarting.rickmortyapp.ui.core.components.PagingLoadingState
import es.smarting.rickmortyapp.ui.core.components.PagingType
import es.smarting.rickmortyapp.ui.core.components.PagingWrapper
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import rickmortyapp.composeapp.generated.resources.Res
import rickmortyapp.composeapp.generated.resources.season_1
import rickmortyapp.composeapp.generated.resources.season_2
import rickmortyapp.composeapp.generated.resources.season_3
import rickmortyapp.composeapp.generated.resources.season_4
import rickmortyapp.composeapp.generated.resources.season_5
import rickmortyapp.composeapp.generated.resources.season_6
import rickmortyapp.composeapp.generated.resources.season_7

@Composable
fun EpisodesScreen (){
    val episodesViewModel = koinViewModel<EpisodesViewModel>()

    val state by episodesViewModel.state.collectAsState()
    val episodes = state.episodes.collectAsLazyPagingItems()

    Box(Modifier.fillMaxSize().background(Color.Blue)) {
        PagingWrapper(
            pagingType = PagingType.ROW,
            pagingItems = episodes,
            initialView = { PagingLoadingState() },
            itemView = {  EpisodeItemList(it) }
        )
    }
}

@Composable
fun EpisodeItemList(episode: EpisodeModel) {
    Column (
        modifier = Modifier.width(120.dp)
            .padding(horizontal = 8.dp)
            .clickable {  }
    ) {
        Image(
            modifier = Modifier.height(200.dp)
                .fillMaxWidth(),
            contentDescription = null,
            contentScale = ContentScale.Inside,
            painter = painterResource(getSeasonImage(episode.season))
        )
    }
}

fun getSeasonImage(seasonEpisode: SeasonEpisode): DrawableResource {
    return when (seasonEpisode) {
        SEASON_1 -> Res.drawable.season_1
        SEASON_2 -> Res.drawable.season_2
        SEASON_3 -> Res.drawable.season_3
        SEASON_4 -> Res.drawable.season_4
        SEASON_5 -> Res.drawable.season_5
        SEASON_6 -> Res.drawable.season_6
        SEASON_7 -> Res.drawable.season_7
        UNKNOWN -> Res.drawable.season_1
    }
}
