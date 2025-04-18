package es.smarting.rickmortyapp.ui.home.tabs.episodes

import androidx.compose.animation.AnimatedContent
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import app.cash.paging.compose.collectAsLazyPagingItems
import es.smarting.rickmortyapp.domain.model.EpisodeModel
import es.smarting.rickmortyapp.domain.model.SeasonEpisode
import es.smarting.rickmortyapp.domain.model.SeasonEpisode.*
import es.smarting.rickmortyapp.ui.core.BackgroundPlaceholderColor
import es.smarting.rickmortyapp.ui.core.BackgroundPrimaryColor
import es.smarting.rickmortyapp.ui.core.DefaultTextColor
import es.smarting.rickmortyapp.ui.core.components.PagingLoadingState
import es.smarting.rickmortyapp.ui.core.components.PagingType
import es.smarting.rickmortyapp.ui.core.components.PagingWrapper
import es.smarting.rickmortyapp.ui.core.components.VideoPlayer
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import rickmortyapp.composeapp.generated.resources.Res
import rickmortyapp.composeapp.generated.resources.placeholder_morty
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

    Column (Modifier.fillMaxSize().background(BackgroundPrimaryColor)) {
        Spacer(Modifier.height(16.dp))
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
    AnimatedContent (urlVideo.isNotBlank()) { condition ->
        if(condition) {
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
        } else  {

            ElevatedCard(
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                colors = CardDefaults.elevatedCardColors(containerColor = BackgroundPlaceholderColor)
            ){

                Column (horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(painter = painterResource(Res.drawable.placeholder_morty), null)
                    Spacer(Modifier.height(4.dp))
                    Text(
                        text = "Aw, jeez, you gotta click the video, guys! I mean, it might be important or something",
                        color = DefaultTextColor,
                        fontStyle = FontStyle.Italic,
                        modifier = Modifier.padding(16.dp)
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
            modifier = Modifier.height(180.dp)
                .fillMaxWidth(),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            painter = painterResource(getSeasonImage(episode.season))
        )
        Spacer(Modifier.height(2.dp))
        Text(episode.episode, color = DefaultTextColor, fontWeight = FontWeight.Bold)
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
