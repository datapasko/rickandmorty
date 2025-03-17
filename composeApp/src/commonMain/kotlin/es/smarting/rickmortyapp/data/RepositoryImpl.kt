package es.smarting.rickmortyapp.data

import app.cash.paging.Pager
import app.cash.paging.PagingConfig
import app.cash.paging.PagingData
import es.smarting.rickmortyapp.data.database.RickMortyDatabase
import es.smarting.rickmortyapp.data.database.entity.CharacterEntity
import es.smarting.rickmortyapp.data.remote.ApiService
import es.smarting.rickmortyapp.data.remote.paging.CharacterPagingSource
import es.smarting.rickmortyapp.data.remote.paging.EpisodesPagingSource
import es.smarting.rickmortyapp.domain.Repository
import es.smarting.rickmortyapp.domain.model.CharacterModel
import es.smarting.rickmortyapp.domain.model.CharacterOfTheDayModel
import es.smarting.rickmortyapp.domain.model.EpisodeModel
import kotlinx.coroutines.flow.Flow

class RepositoryImpl(
    private val api:ApiService,
    private val characterPagingSource: CharacterPagingSource,
    private val episodesPagingSource: EpisodesPagingSource,
    private val rickMortyDatabase: RickMortyDatabase
):Repository {

    companion object {
        const val MAX_ITEMS = 20
        const val PREFETCH_ITEMS = 5
    }
    override suspend fun getSingleCharacter(id: String): CharacterModel {
        return api.getSingleCharacter(id).toDomain()
    }

    override fun getAllCharacters(): Flow<PagingData<CharacterModel>> {
        return Pager(
            config = PagingConfig(pageSize = MAX_ITEMS, prefetchDistance = PREFETCH_ITEMS),
            pagingSourceFactory = {characterPagingSource}
        ).flow
    }

    override fun getAllEpisodes(): Flow<PagingData<EpisodeModel>> {
        return Pager(
            config = PagingConfig(pageSize = MAX_ITEMS, prefetchDistance = PREFETCH_ITEMS),
            pagingSourceFactory = {episodesPagingSource}
        ).flow
    }

    override suspend fun getCharacterDB(): CharacterOfTheDayModel? {
        return rickMortyDatabase.getPreferencesDAO().getCharacters()?.toDomain()
    }

    override suspend fun saveCharacter(characterOfTheDayModel: CharacterOfTheDayModel) {
        rickMortyDatabase.getPreferencesDAO().saveCharacter(characterEntity = characterOfTheDayModel.toEntity())
    }
}