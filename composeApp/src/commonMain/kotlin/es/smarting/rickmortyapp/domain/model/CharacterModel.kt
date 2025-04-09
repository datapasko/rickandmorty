package es.smarting.rickmortyapp.domain.model

data class CharacterModel(
    val id: Int,
    val isAlive: Boolean,
    val image: String,
    val name: String,
    val species: String,
    val gender: String,
    val origin: String,
    val episode: List<String>
)
