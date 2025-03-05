package es.smarting.rickmortyapp.data

import app.cash.paging.Pager
import app.cash.paging.PagingConfig
import app.cash.paging.PagingData
import es.smarting.rickmortyapp.data.remote.ApiService
import es.smarting.rickmortyapp.data.remote.paging.CharacterPagingSource
import es.smarting.rickmortyapp.domain.Repository
import es.smarting.rickmortyapp.domain.model.CharacterModel
import kotlinx.coroutines.flow.Flow

class RepositoryImpl(
    private val api:ApiService,
    private val characterPagingSource: CharacterPagingSource
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
}