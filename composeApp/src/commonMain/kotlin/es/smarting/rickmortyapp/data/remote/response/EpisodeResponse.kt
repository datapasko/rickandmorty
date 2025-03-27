package es.smarting.rickmortyapp.data.remote.response

import es.smarting.rickmortyapp.domain.model.EpisodeModel
import es.smarting.rickmortyapp.domain.model.SeasonEpisode
import es.smarting.rickmortyapp.domain.model.SeasonEpisode.*
import kotlinx.serialization.Serializable

@Serializable
data class EpisodeResponse(
    val id: Int,
    val name: String,
    val episode: String,
    val characters: List<String>
) {
    fun toDomain(): EpisodeModel {
        val season = getSeasonFromEpisodeCode(episode)
        return EpisodeModel(
            id = id,
            name = name,
            episode = episode,
            characters = characters.map { url -> url.substringAfter("/") },
            season = season,
            videoURL = getVideoUrlFromSeason(season)

        )
    }

    private fun getVideoUrlFromSeason(season: SeasonEpisode): String {
        return when (season) {
            SEASON_1 -> "https://firebasestorage.googleapis.com/v0/b/rickmortykmp-e1321.firebasestorage.app/o/trailer_rick_morty.mp4?alt=media&token=c147f11c-5d38-4673-a5da-a298b63e5b05"
            SEASON_2 -> "https://firebasestorage.googleapis.com/v0/b/rickmortykmp-e1321.firebasestorage.app/o/trailer_rick_morty.mp4?alt=media&token=c147f11c-5d38-4673-a5da-a298b63e5b05"
            SEASON_3 -> "https://firebasestorage.googleapis.com/v0/b/rickmortykmp-e1321.firebasestorage.app/o/trailer_rick_morty.mp4?alt=media&token=c147f11c-5d38-4673-a5da-a298b63e5b05"
            SEASON_4 -> "https://firebasestorage.googleapis.com/v0/b/rickmortykmp-e1321.firebasestorage.app/o/trailer_rick_morty.mp4?alt=media&token=c147f11c-5d38-4673-a5da-a298b63e5b05"
            SEASON_5 -> "https://firebasestorage.googleapis.com/v0/b/rickmortykmp-e1321.firebasestorage.app/o/trailer_rick_morty.mp4?alt=media&token=c147f11c-5d38-4673-a5da-a298b63e5b05"
            SEASON_6 -> "https://firebasestorage.googleapis.com/v0/b/rickmortykmp-e1321.firebasestorage.app/o/trailer_rick_morty.mp4?alt=media&token=c147f11c-5d38-4673-a5da-a298b63e5b05"
            SEASON_7 -> "https://firebasestorage.googleapis.com/v0/b/rickmortykmp-e1321.firebasestorage.app/o/trailer_rick_morty.mp4?alt=media&token=c147f11c-5d38-4673-a5da-a298b63e5b05"
            UNKNOWN -> "https://firebasestorage.googleapis.com/v0/b/rickmortykmp-e1321.firebasestorage.app/o/trailer_rick_morty.mp4?alt=media&token=c147f11c-5d38-4673-a5da-a298b63e5b05"
        }
    }

    private fun getSeasonFromEpisodeCode(episode: String): SeasonEpisode {
        return when {
            episode.startsWith("S01") -> SEASON_1
            episode.startsWith("S02") -> SEASON_2
            episode.startsWith("S03") -> SEASON_3
            episode.startsWith("S04") -> SEASON_4
            episode.startsWith("S05") -> SEASON_5
            episode.startsWith("S06") -> SEASON_6
            episode.startsWith("S07") -> SEASON_7
            else -> UNKNOWN
        }
    }
}
