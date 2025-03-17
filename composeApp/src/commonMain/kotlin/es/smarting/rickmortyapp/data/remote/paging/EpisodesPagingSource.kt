package es.smarting.rickmortyapp.data.remote.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import es.smarting.rickmortyapp.data.remote.ApiService
import es.smarting.rickmortyapp.domain.model.CharacterModel
import es.smarting.rickmortyapp.domain.model.EpisodeModel
import kotlinx.io.IOException

class EpisodesPagingSource(
    private val api: ApiService
): PagingSource<Int, EpisodeModel> (){

    override fun getRefreshKey(state: PagingState<Int, EpisodeModel>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, EpisodeModel> {
        return try {
            val page = params.key ?: 1
            val response = api.getAllEpisodes(page)
            val episodes = response.result

            val prev = if (page > 0) page - 1 else null
            val next = if (response.info.next != null) page+1 else null

            LoadResult.Page(
                data = episodes.map{ episode -> episode.toDomain() },
                prevKey = prev,
                nextKey = next
            )

        }catch (exception: IOException) {
            LoadResult.Error(exception)
        }
    }
}