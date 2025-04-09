package es.smarting.rickmortyapp.ui.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import es.smarting.rickmortyapp.domain.model.CharacterModel
import es.smarting.rickmortyapp.domain.model.EpisodeModel
import es.smarting.rickmortyapp.ui.core.ext.aliveBorder
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf
import rickmortyapp.composeapp.generated.resources.Res
import rickmortyapp.composeapp.generated.resources.space

@Composable
fun CharacterDetailScreen (
    id: Int
) {

    val characterDetailViewModel = koinViewModel<CharacterDetailViewModel> ( parameters = { parametersOf(id) }  )
    val state by characterDetailViewModel.state.collectAsState()
    val scroll = rememberScrollState()

    Column (
        modifier = Modifier.fillMaxSize()
            .background(Color.White)
            .verticalScroll(scroll)
    ) {
        state.character?.let { character ->
            MainHeader(character)
            CharacterInformation(character)
        }

        CharacterEpisodesList(state.episodes)
    }
}

@Composable
fun CharacterEpisodesList(episodes: List<EpisodeModel>?) {
    ElevatedCard(
        modifier = Modifier.padding(16.dp)
            .fillMaxWidth()
    ){
        Box(contentAlignment = Alignment.Center) {
            if (episodes == null) {
                CircularProgressIndicator(color = Color.Green)
            } else {
                Column {
                    episodes.forEach { episode ->
                        EpisodeItem(episode)
                    }
                }
            }
        }
    }

}

@Composable
fun EpisodeItem(episode: EpisodeModel) {
    Text(episode.name)
    Text(episode.episode)
}

@Composable
fun CharacterInformation(character: CharacterModel) {
    ElevatedCard(
        modifier = Modifier.padding(16.dp)
            .fillMaxWidth()
    ){
        Column (
            modifier = Modifier.padding(16.dp)
        ) {
            Text("ABOUT THE CHARACTER")
            Spacer(modifier = Modifier.height(4.dp))
            InformationDetail("Origin", character.origin)
            Spacer(modifier = Modifier.height(2.dp))
            InformationDetail("Gender", character.gender)
        }
    }
}

@Composable
fun InformationDetail(title: String, detail: String) {
    Row {
        Text(title, color = Color.Black, fontWeight = FontWeight.Bold)
        Text(detail, color = Color.Green)
    }
}

@Composable
fun MainHeader (characterModel: CharacterModel) {

    Box(
        modifier = Modifier.fillMaxWidth()
            .height(300.dp)
    ) {
        Image(
            painter = painterResource(Res.drawable.space),
            contentDescription = "Background detail",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        CharacterHeader(characterModel)
    }

}

@Composable
fun CharacterHeader(
    characterModel: CharacterModel
) {
    Box (
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ){
        Column (
            modifier = Modifier.fillMaxWidth()
                .height(100.dp)
                .clip(RoundedCornerShape(topStartPercent = 10, topEndPercent = 10))
                .background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center

        ){
            Text (
                text = characterModel.name ,
                color = Color.Black,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                "Species: ${characterModel.species} ${characterModel.episode.size}",
                color = Color.Black,
                fontSize = 20.sp
            )

        }

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            Box(
                contentAlignment = Alignment.TopCenter
            ) {
                Box (
                    modifier = Modifier.size(205.dp)
                        .clip(CircleShape)
                        .background(Color.Black.copy(alpha = 0.15f)),
                    contentAlignment = Alignment.Center
                ) {
                    AsyncImage(
                        model = characterModel.image,
                        contentDescription = null,
                        modifier = Modifier.size(190.dp)
                            .clip(CircleShape)
                            .aliveBorder(characterModel.isAlive),
                        contentScale = ContentScale.Crop
                    )
                }

                val aliveCopy = if(characterModel.isAlive) "ALIVE" else "DEAD"
                val colorCopy = if(characterModel.isAlive) Color.Green else Color.Red

                Text(
                    text = aliveCopy,
                    color = Color.White,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.clip(RoundedCornerShape(30))
                        .background(colorCopy)
                        .padding(horizontal = 6.dp, vertical = 2.dp)
                )
            }
            Spacer(Modifier.weight(1f))

        }
    }

}
