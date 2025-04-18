package es.smarting.rickmortyapp.data.remote.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import es.smarting.rickmortyapp.data.remote.ApiService
import es.smarting.rickmortyapp.domain.model.CharacterModel
import kotlinx.io.IOException

class CharacterPagingSource (
    private val api:ApiService
) : PagingSource<Int, CharacterModel> (){

    override fun getRefreshKey(state: PagingState<Int, CharacterModel>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterModel> {
        return try {
            val page = params.key ?: 1
            val response = api.getAllCharacters(page)
            val characters = response.results

            println(response.info)

            val prev = if (page > 0) page - 1 else null
            val next = if (response.info.next != null) page+1 else null

            LoadResult.Page(
                data = characters.map{ character -> character.toDomain()},
                prevKey = prev,
                nextKey = next
            )

        }catch (exception: IOException) {
            LoadResult.Error(exception)
        }
    }
}