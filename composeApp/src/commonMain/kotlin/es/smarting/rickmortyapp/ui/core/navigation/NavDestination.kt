package es.smarting.rickmortyapp.ui.core.navigation

import kotlinx.serialization.Serializable

@Serializable
object HomeNav

@Serializable
object EpisodesNav

@Serializable
object CharactersNav

@Serializable
data class CharacterDetail( val id: Int)