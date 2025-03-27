package es.smarting.rickmortyapp.ui.home.tabs.episodes

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
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
import es.smarting.rickmortyapp.ui.core.components.VideoPlayer
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import rickmortyapp.composeapp.generated.resources.Res
import rickmortyapp.composeapp.generated.resources.portal_rick
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

    Column (Modifier.fillMaxSize()) {
        PagingWrapper(
            pagingType = PagingType.ROW,
            pagingItems = episodes,
            initialView = { PagingLoadingState() },
            itemView = {  EpisodeItemList(it) { url -> episodesViewModel.onPlaySelected(url) } }
        )

        EpisodePlayer(state.playVideo) { episodesViewModel.onCloseVideo()}


    }
}

@Composable
fun EpisodePlayer(urlVideo: String, onCloseVideo: () -> Unit) {
    AnimatedVisibility (urlVideo.isNotBlank()) {
        ElevatedCard(
            modifier = Modifier.fillMaxWidth()
                .height(250.dp)
                .padding(16.dp)
                .border(3.dp, Color.Green, CardDefaults.elevatedShape)
        ){
            Box(modifier = Modifier.background(Color.Black)){
                Box(
                    modifier = Modifier.padding(16.dp),
                    contentAlignment = Alignment.Center
                ){
                    VideoPlayer(Modifier.fillMaxWidth().height(200.dp), urlVideo)
                }
                Row {
                    Spacer(modifier = Modifier.weight(1f))
                    Image(
                        painter = painterResource(Res.drawable.portal_rick),
                        contentDescription = "",
                        modifier = Modifier.padding(16.dp).size(30.dp).clickable { onCloseVideo() }
                    )
                }
            }
        }
    }
}

@Composable
fun EpisodeItemList(episode: EpisodeModel, onEpisodeSelected: (String) -> Unit) {
    Column (
        modifier = Modifier.width(120.dp)
            .padding(horizontal = 8.dp)
            .clickable {
               onEpisodeSelected(episode.videoURL)
            }
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
